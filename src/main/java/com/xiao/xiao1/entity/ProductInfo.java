package com.xiao.xiao1.entity;

/*
@auther XiaoRuiQing
@Date 2019/1/23
*/


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xiao.xiao1.enums.ProductStatusEnum;
import com.xiao.xiao1.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/*
 *
 * 商品
 * */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    /**
     * 商品的id
     */
    @Id
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
     * 商品的库存
     */
    private Integer productStock;

    /**
     * 商品的描述
     */
    private String productDescription;

    /**
     * 商品的小图
     */
    private String productIcon;

    /**
     * 商品的状态（0：正常， 1：下架）
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /**
     * 商品的类目编号
     */
    private Integer categoryType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

}
