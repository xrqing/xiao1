package com.xiao.xiao1.mapper;

import com.xiao.xiao1.entity.ProductInfo;
import com.xiao.xiao1.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class ProductInfoMapperTest {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    /**
     * 插入数据
     */
    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("34");
        productInfo.setProductName("牛奶");
        productInfo.setProductPrice(new BigDecimal(5.5));
        productInfo.setProductStock(40);
        productInfo.setProductDescription("早餐奶");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(3);
        productInfo.setCreateTime(new Date());
        productInfo.setUpdateTime(new Date());
        ProductInfo result = productInfoMapper.save(productInfo);
        Assert.assertNotNull(result);
        System.out.println("插入的数据：" + result);
    }


    /**
     * 商品上架信息（根据状态去查询）
     */
    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = productInfoMapper.findByProductStatus(0);
        Assert.assertNotEquals(0, productInfoList.size());
        System.out.println("商品的上架信息:" + productInfoList);
    }
}