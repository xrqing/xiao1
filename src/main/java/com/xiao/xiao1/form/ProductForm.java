package com.xiao.xiao1.form;
/*
@auther XiaoRuiQing
@Date 2019/1/29
*/

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 表单验证
 * */
@Data
public class ProductForm {
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
}
