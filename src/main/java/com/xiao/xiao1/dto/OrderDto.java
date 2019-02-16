package com.xiao.xiao1.dto;

/*
@auther XiaoRuiQing
@Date 2019/1/24
*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xiao.xiao1.entity.OrderDetail;
import com.xiao.xiao1.enums.OrderStatusEnum;
import com.xiao.xiao1.enums.PaySattusEnum;
import com.xiao.xiao1.utils.Date2LongSerializer;
import com.xiao.xiao1.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 数据传输对象
 */
@Data
public class OrderDto {

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家的地址
     */
    private String buyerAddress;

    /**
     * 买家微信OpenId
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态，默认为新下单
     */
    private Integer orderStatus;

    /**
     * 支付状态，默认为0未支付
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /**
     * 订单详情列
     */
    List<OrderDetail> orderDetailList;

    /**
     * 返回列表订单状态 (@JsonIgnore 对象转换成json格式就会忽律掉这个方法)
     */
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    /**
     * 返回列表支付状态 (@JsonIgnore 对象转换成json格式就会忽律掉这个方法)
     */
    @JsonIgnore
    public PaySattusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PaySattusEnum.class);
    }
}
