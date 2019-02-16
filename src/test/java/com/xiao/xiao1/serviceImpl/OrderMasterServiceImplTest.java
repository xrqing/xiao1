package com.xiao.xiao1.serviceImpl;

import com.xiao.xiao1.dto.OrderDto;
import com.xiao.xiao1.entity.OrderDetail;
import com.xiao.xiao1.enums.OrderStatusEnum;
import com.xiao.xiao1.enums.PaySattusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
@auther XiaoRuiQing
@Date 2019/1/25
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterServiceImpl orderMasterService;

    /**
     * 设置微信号
     */
    private final String BUYER_OPENID = "110110";

    /**
     * 设置订单编号
     */
    private final String ORDER_ID = "1548374737332865750";

    /*
     * 创建订单
     * */
    @Test
    public void create() {
        /** 下订单信息 */
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("小肖");
        orderDto.setBuyerAddress("中国江西信丰");
        orderDto.setBuyerPhone("13760389469");
        orderDto.setBuyerOpenid(BUYER_OPENID);
        orderDto.setUpdateTime(new Date());
        orderDto.setCreateTime(new Date());

        /** 购物车信息 */
        List<OrderDetail> orderDetailList = new ArrayList<>();

        /** 购买的第一个商品 */
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("134");
        orderDetail.setProductQuantity(2);
        orderDetail.setUpdateTime(new Date());
        orderDetail.setCreateTime(new Date());
        orderDetailList.add(orderDetail);

        /** 购买的第二个商品 */
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("123");
        orderDetail2.setProductQuantity(4);
        orderDetail2.setCreateTime(new Date());
        orderDetail2.setUpdateTime(new Date());
        orderDetailList.add(orderDetail2);

        /**设置到orderdto  */
        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result = orderMasterService.create(orderDto);
        log.info("【创建订单】result = {}", result);
        Assert.assertNotNull(result);
        System.out.println("下的订单为：" + result);
    }

    /*
     * 查询单个订单
     * */
    @Test
    public void findOne() {
        OrderDto result = orderMasterService.findOne(ORDER_ID);
        log.info("【查询的订单】result={}", result);
        /** 查询的订单编号与获取的订单编号一致，则返回成功 */
        Assert.assertEquals(ORDER_ID, result.getOrderId());
        System.out.println("查询的订单:" + result);
    }

    /*
     * 查询订单列表
     * */
    @Test
    public void findList() {
        PageRequest request = new PageRequest(0, 4);
        Page<OrderDto> orderDtoPage = orderMasterService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0, orderDtoPage.getTotalElements());
        System.out.println("订单信息列表:" + orderDtoPage);

    }

    /*
     * 取消订单
     * */
    @Test
    public void cancel() {
        OrderDto orderDto = orderMasterService.findOne(ORDER_ID);
        OrderDto result = orderMasterService.cancel(orderDto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
        System.out.println("取消的订单为：" + result);
    }

    /*
     * 完结订单
     * */
    @Test
    public void finish() {
        OrderDto orderDto = orderMasterService.findOne(ORDER_ID);
        OrderDto result = orderMasterService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
        System.out.println("完结订单信息:+result");
    }

    /*
     * 支付订单
     * */
    @Test
    public void paid() {
        OrderDto orderDto = orderMasterService.findOne(ORDER_ID);
        OrderDto result = orderMasterService.paid(orderDto);
        Assert.assertEquals(PaySattusEnum.SUCCESS.getCode(), result.getPayStatus());
        System.out.println("支付完成订单信息:" + result);
    }

    /**
     * 卖家端订单列表
     */
    @Test
    public void findList1() {
        PageRequest request = new PageRequest(0, 10);
        Page<OrderDto> orderDtoPage = orderMasterService.findList(request);
        //Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
        Assert.assertTrue("查询所有的订单列表：", orderDtoPage.getTotalElements() > 0);
        System.out.println(orderDtoPage);
    }
}