package com.xiao.xiao1.service;

/*
@auther XiaoRuiQing
@Date 2019/1/24
*/

import com.xiao.xiao1.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderMasterService {

    /**
     * 创建订单
     */
    OrderDto create(OrderDto orderDto);

    /**
     * 查询单个订单
     */
    OrderDto findOne(String orderId);

    /**
     * 根据微信号询订单列表
     */
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     */
    OrderDto cancel(OrderDto orderDto);

    /**
     * 完结订单
     */
    OrderDto finish(OrderDto orderDto);

    /**
     * 支付订单
     */
    OrderDto paid(OrderDto orderDto);

    /**
     * 卖家端订单列表
     */
    Page<OrderDto> findList(Pageable pageable);
}
