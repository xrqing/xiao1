package com.xiao.xiao1.serviceImpl;

/*
@auther XiaoRuiQing
@Date 2019/1/22
*/


import com.xiao.xiao1.entity.ProductCategory;
import com.xiao.xiao1.mapper.ProductCategoryMapper;
import com.xiao.xiao1.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * 类目
 * */
@Service
public class ProductCateTypeServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 查询单个
     */
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryMapper.findOne(categoryId);
    }

    /**
     * 查询所有
     */
    @Override
    public List<ProductCategory> findAll() {
        return productCategoryMapper.findAll();
    }

    /**
     * 查询管理的商品类目列表分页
     */
    @Override
    public Page<ProductCategory> findAll(Pageable pageable) {
        return productCategoryMapper.findAll(pageable);
    }

    /**
     * 查询所有（买家端使用：按类别编号查询）
     */
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryMapper.findByCategoryTypeIn(categoryTypeList);
    }

    /**
     * 新增
     */
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryMapper.save(productCategory);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Integer categoryId) {
        productCategoryMapper.delete(categoryId);
    }

}
