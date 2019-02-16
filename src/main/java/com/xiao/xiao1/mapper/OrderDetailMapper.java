package com.xiao.xiao1.mapper;

/*
@auther XiaoRuiQing
@Date 2019/1/24
*/

import com.xiao.xiao1.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailMapper extends JpaRepository<OrderDetail, String> {

    /**
     * 根据订单号去查询订单详情
     */
    List<OrderDetail> findByOrderId(String orderId);
}
