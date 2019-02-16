package com.xiao.xiao1.service;

/*
@auther XiaoRuiQing
@Date 2019/1/22
*/

import com.xiao.xiao1.entity.ProductCategory;
import com.xiao.xiao1.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
 * 类目
 * */
public interface ProductCategoryService {

    /**
     * 查询单个
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * 查询管理的商品类目列表
     */
    List<ProductCategory> findAll();

    /**
     * 查询管理的商品类目列表分页
     */
    Page<ProductCategory> findAll(Pageable pageable);

    /**
     * 查询所有（买家端使用：按类别编号查询）
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 新增
     */
    ProductCategory save(ProductCategory productCategory);

    /**
     * 删除
     */
    void delete(Integer categoryId);
}
