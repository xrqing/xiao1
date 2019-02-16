package com.xiao.xiao1.serviceImpl;

/*
@auther XiaoRuiQing
@Date 2019/1/24
*/

import com.xiao.xiao1.convert.OrderMaster2OrderDtoConverter;
import com.xiao.xiao1.dto.CartDto;
import com.xiao.xiao1.dto.OrderDto;
import com.xiao.xiao1.entity.OrderDetail;
import com.xiao.xiao1.entity.OrderMaster;
import com.xiao.xiao1.entity.ProductInfo;
import com.xiao.xiao1.enums.OrderStatusEnum;
import com.xiao.xiao1.enums.PaySattusEnum;
import com.xiao.xiao1.enums.ResultEnum;
import com.xiao.xiao1.exception.SellException;
import com.xiao.xiao1.mapper.OrderDetailMapper;
import com.xiao.xiao1.mapper.OrderMasterMapper;
import com.xiao.xiao1.service.OrderMasterService;
import com.xiao.xiao1.service.ProductInfoService;
import com.xiao.xiao1.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.channels.SeekableByteChannel;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    /*
     * 创建订单
     * */
    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {

        /** 定义金额 */
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        /** 生成随机的订单编号 */
        String orderId = KeyUtil.genUniqueKey();

        /** 1:查询商品（数量及价格） */
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            /** 2:计算订单总价 */
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            /** 3:订单详情入库 */
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailMapper.save(orderDetail);
        }

        /** 4:写订单进数据库 (先拷贝在添加，否则订单为空，报空指针异常)*/
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PaySattusEnum.WAIT.getCode());
        orderMasterMapper.save(orderMaster);

        /** 5: 减库存 */
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDtoList);
        return orderDto;
    }

    /*
     * 查询单个订单
     * */
    @Override
    public OrderDto findOne(String orderId) {

        /** 先查订单号 */
        OrderMaster orderMaster = orderMasterMapper.findOne(orderId);
        if (orderId == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        /** 查订单详情 */
        List<OrderDetail> orderDetailList = orderDetailMapper.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        /** 保存在数据传输对象中 */
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    /*
     * 查询订单列表
     * */
    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterMapper.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDtoConverter.convert(orderMasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<>(orderDtoList, pageable, orderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    /*
     * 取消订单
     * */
    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {

        /** 数据转换 */
        OrderMaster orderMaster = new OrderMaster();

        /**1:判断订单状态 */
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确, orderId={},orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        /**2:修改订单的状态 */
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterMapper.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单取消】更新失败,orderMaster = {}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        /** 3:返回库存 */
        /**先判断是否有商品，为了代码的健壮 */
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("取消订单】订单中无商品详情，orderDto={}", orderDto);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        /** 加库存 */
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(cartDtoList);

        /** 退款 */
        if (orderDto.getPayStatus().equals(PaySattusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDto;
    }

    /*
     * 完结订单
     * */
    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {

        /** 数据转换 */
        OrderMaster orderMaster = new OrderMaster();

        /** 判断订单状态 */
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确，orderId={},orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        /** 修改订单状态 */
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterMapper.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败,orderMaster = {}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    /*
     * 支付订单
     * */
    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {

        /** 数据的转换 */
        OrderMaster orderMaster = new OrderMaster();

        /** 判断订单的状态 */
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单成功】订单状态不正确,orderId={},orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        /**判断支付状态*/
        if (!orderDto.getPayStatus().equals(PaySattusEnum.WAIT.getCode())) {
            log.error("【订单支付成功】订单状态不正确,orderDto={}", orderDto);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        /**修改支付状态 */
        orderDto.setPayStatus(PaySattusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterMapper.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付成功】更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    /**
     * 卖家端订单列表
     */
    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterMapper.findAll(pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDtoConverter.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDtoList, pageable, orderMasterPage.getTotalElements());
    }
}
