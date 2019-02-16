package com.xiao.xiao1.service;

/*
@auther XiaoRuiQing
@Date 2019/1/26
*/

import com.xiao.xiao1.dto.OrderDto;

/*
 * 买家通用方法
 * */
public interface BuyerService {
    /**
     * 查询一个订单
     */
    OrderDto findOrderone(String openid, String orderId);

    /**
     * 取消订单
     */
    OrderDto cancelOrder(String openid, String orderId);
}
