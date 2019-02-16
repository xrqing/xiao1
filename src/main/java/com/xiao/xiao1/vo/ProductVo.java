package com.xiao.xiao1.vo;

/*
@auther XiaoRuiQing
@Date 2019/1/23
*/


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/*
 * 商品包含类目
 * */
@Data
public class ProductVo {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
