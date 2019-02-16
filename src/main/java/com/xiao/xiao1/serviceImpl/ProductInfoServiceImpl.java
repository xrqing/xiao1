package com.xiao.xiao1.serviceImpl;

/*
@auther XiaoRuiQing
@Date 2019/1/23
*/

import com.xiao.xiao1.dto.CartDto;
import com.xiao.xiao1.entity.ProductInfo;
import com.xiao.xiao1.enums.ProductStatusEnum;
import com.xiao.xiao1.enums.ResultEnum;
import com.xiao.xiao1.exception.SellException;
import com.xiao.xiao1.mapper.ProductInfoMapper;
import com.xiao.xiao1.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    /**
     * 查询当个
     */
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoMapper.findOne(productId);
    }

    /**
     * 查询所在的在架商品列表
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoMapper.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    /**
     * 查询管理的商品列表分页
     */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoMapper.findAll(pageable);
    }

    /**
     * 插入数据
     */
    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoMapper.save(productInfo);
    }

    /**
     * 加库存
     */
    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtoList) {
        /** 查询 */
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = productInfoMapper.findOne(cartDto.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            /** 加库存 */
            Integer result = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoMapper.save(productInfo);
        }
    }

    /**
     * 减库存
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
        /** 查询 */
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = productInfoMapper.findOne(cartDto.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            /** 减库存（判断结果是否小于0，小于0则抛出错误） */
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoMapper.save(productInfo);
        }
    }

    /**
     * 上架
     */
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoMapper.findOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoMapper.save(productInfo);
    }

    /**
     * 下架
     */
    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoMapper.findOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoMapper.save(productInfo);
    }
}
















