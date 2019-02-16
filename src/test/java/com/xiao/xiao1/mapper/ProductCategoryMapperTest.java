package com.xiao.xiao1.mapper;


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

/*
@auther XiaoRuiQing
@Date 2019/1/22
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 插入数据
     */
    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女学生");
        productCategory.setCategoryType(11);
        productCategory.setCreateTime(new Date());
        productCategory.setUpdateTime(new Date());
        productCategoryMapper.save(productCategory);
    }

    /**
     * 更新数据
     */
    @Test
    public void updateTest() {
        ProductCategory productCategory = productCategoryMapper.findOne(2);
        productCategory.setCategoryType(14);
        productCategoryMapper.save(productCategory);
    }

    /**
     * 查询
     */
    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryMapper.findOne(3);
        System.out.println(productCategory.toString());
    }

    /**
     * 删除
     */
    @Test
    public void delete() {
        ProductCategory category = productCategoryMapper.findOne(5);
        productCategoryMapper.delete(5);
        System.out.println(category.toString());
    }

    /**
     * 买家端查询接口的测试
     */
    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(2, 3, 4, 5, 6, 7);
        List<ProductCategory> result = productCategoryMapper.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }

}