package com.xiao.xiao1.entity;

/*
@auther XiaoRuiQing
@Date 2019/1/23
*/

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 商品的id
     */
    private String productId;

    /**
     * 商品的名字
     */
    private String productName;

    /**
     * 商品的单价
     */
    private BigDecimal productPrice;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    /**
     * 商品的小图
     */
    private String productIcon;

    /**
     * 更新时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}































