package com.xiao.xiao1.dto;

/*
@auther XiaoRuiQing
@Date 2019/1/24
*/


import lombok.Data;


/*
 * 数据传输对象(购物车)
 * */
@Data
public class CartDto {

    /**
     * 商品的id
     */
    private String productId;

    /**
     * 商品的数量
     */
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
