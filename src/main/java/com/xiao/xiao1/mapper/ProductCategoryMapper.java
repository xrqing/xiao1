package com.xiao.xiao1.mapper;
/*
@auther XiaoRuiQing
@Date 2019/1/22
*/

import com.xiao.xiao1.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryMapper extends JpaRepository<ProductCategory, Integer> {

    /**
     * 查询所有（买家端使用：按类别编号查询）
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
