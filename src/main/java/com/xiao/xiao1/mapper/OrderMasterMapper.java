package com.xiao.xiao1.mapper;

/*
@auther XiaoRuiQing
@Date 2019/1/24
*/

import com.xiao.xiao1.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterMapper extends JpaRepository<OrderMaster, String> {

    /**
     * 根据买家的openID来查并展示分页
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
