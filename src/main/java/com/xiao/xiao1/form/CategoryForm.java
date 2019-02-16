package com.xiao.xiao1.form;

import lombok.Data;

/*
@auther XiaoRuiQing
@Date 2019/1/30
*/

@Data
public class CategoryForm {

    /**
     * 类目id
     */
    private Integer categoryId;

    /**
     * 类目名字
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;
}
