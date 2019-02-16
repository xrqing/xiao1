package com.xiao.xiao1.entity;
/*
@auther XiaoRuiQing
@Date 2019/1/22
*/


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/*
 * 类目
 * */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {

    /**
     * 类目id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**
     * 类目名字
     */
    private String categoryName;

    /**
     * 类目编号
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

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType, Date createTime, Date updateTime) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
