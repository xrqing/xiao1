package com.xiao.xiao1.service;
/*
@auther XiaoRuiQing
@Date 2019/1/23
*/


import com.xiao.xiao1.dto.CartDto;
import com.xiao.xiao1.entity.ProductInfo;
import com.xiao.xiao1.enums.ProductStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 */
public interface ProductInfoService {

    /**
     * 查询单个
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所在的在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询管理的商品列表分页
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 插入数据
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     */
    void increaseStock(List<CartDto> cartDtoList);

    /**
     * 减库存
     */
    void decreaseStock(List<CartDto> cartDtoList);

    /**
     * 上架
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     */
    ProductInfo offSale(String productId);
}

