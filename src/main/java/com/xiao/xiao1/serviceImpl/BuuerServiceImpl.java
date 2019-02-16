package com.xiao.xiao1.serviceImpl;


/*
@auther XiaoRuiQing
@Date 2019/1/26
*/

import com.xiao.xiao1.dto.OrderDto;
import com.xiao.xiao1.enums.ResultEnum;
import com.xiao.xiao1.exception.SellException;
import com.xiao.xiao1.service.BuyerService;
import com.xiao.xiao1.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * 通用方法的实现类
 * */
@Service
@Slf4j
public class BuuerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 查询一个订单
     */
    @Override
    public OrderDto findOrderone(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    /**
     * 取消订单
     */
    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid, orderId);
        if (orderDto == null) {
            log.error("【取消订单】查不到订单，orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderDto orderDto1 = orderMasterService.cancel(orderDto);
        return orderDto1;
    }

    /**
     * 抽取公共的方法
     */
    private OrderDto checkOrderOwner(String openid, String orderId) {
        OrderDto orderDto = orderMasterService.findOne(orderId);
        if (orderDto == null) {
            return null;
        }
        /** 判断是否是自己的订单 */
        if (!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致，openid={},orderId={}", openid, orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
