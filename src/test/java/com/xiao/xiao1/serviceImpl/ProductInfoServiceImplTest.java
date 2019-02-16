package com.xiao.xiao1.serviceImpl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.xiao.xiao1.entity.ProductInfo;
import com.xiao.xiao1.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/*
@auther XiaoRuiQing
@Date 2019/1/23
*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    /**
     * 查询当个
     */
    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("123");
        Assert.assertEquals("123", productInfo.getProductId());
        System.out.println("查询的数据为:" + productInfo);
    }

    /**
     * 查询所在的在架商品列表
     */
    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
        System.out.println("商品列表：" + productInfoList);
    }

    /**
     * 查询管理的商品列表分页
     */
    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0, 2);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
    }

    /**
     * 插入数据
     */
    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("131");
        productInfo.setProductName("香蕉");
        productInfo.setProductPrice(new BigDecimal(1.5));
        productInfo.setProductStock(50);
        productInfo.setProductDescription("水果");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(3);
        productInfo.setCreateTime(new Date());
        productInfo.setUpdateTime(new Date());
        ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
        System.out.println("插入的数据：" + result);
    }

    /**
     * 上架
     */
    @Test
    public void onSale() {
        ProductInfo productInfo = productInfoService.onSale("134");
        Assert.assertEquals(ProductStatusEnum.UP, productInfo.getProductStatusEnum());
    }

    /**
     * 下架
     */
    @Test
    public void offSale() {
        ProductInfo productInfo = productInfoService.offSale("134");
        Assert.assertEquals(ProductStatusEnum.DOWN, productInfo.getProductStatusEnum());
    }
}