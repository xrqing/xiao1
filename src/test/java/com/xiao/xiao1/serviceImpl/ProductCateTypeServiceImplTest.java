package com.xiao.xiao1.serviceImpl;

import com.xiao.xiao1.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/*
@auther XiaoRuiQing
@Date 2019/1/22
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCateTypeServiceImplTest {

    @Autowired
    private ProductCateTypeServiceImpl productCateTypeService;

    /**
     * 查询单个
     */
    @Test
    public void findOne() {
        ProductCategory productCategory = productCateTypeService.findOne(2);
        Assert.assertEquals(new Integer(2), productCategory.getCategoryId());
        System.out.println(productCategory);
    }

    /**
     * 查询全部
     */
    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = productCateTypeService.findAll();
        Assert.assertNotEquals(0, productCategoryList.size());
        System.out.println("查询全部的数据:" + productCategoryList);
    }

    /**
     * 查询所有（买家端使用：按类别编号查询）
     */
    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = productCateTypeService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4, 14, 10));
        Assert.assertNotEquals(0, productCategoryList.size());
        System.out.println("查询所有的买家端编号数据:" + productCategoryList);
    }

    /**
     * 新增
     */
    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("男士", 11, new Date(), new Date());
        ProductCategory result = productCateTypeService.save(productCategory);
        Assert.assertNotNull(result);
    }

    /**
     * 删除
     */
    @Test
    public void delete() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCateTypeService.delete(2);
        System.out.println(productCategory);
    }
}