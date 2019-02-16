package com.xiao.xiao1.mapper;

/*
@auther XiaoRuiQing
@Date 2019/1/23
*/

import com.xiao.xiao1.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品的接口
 */
public interface ProductInfoMapper extends JpaRepository<ProductInfo, String> {

    /**
     * 商品上架信息（根据状态去查询）
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
