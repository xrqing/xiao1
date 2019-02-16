package com.xiao.xiao1.controller;

/*
@auther XiaoRuiQing
@Date 2019/1/23
*/

import com.xiao.xiao1.entity.ProductCategory;
import com.xiao.xiao1.entity.ProductInfo;
import com.xiao.xiao1.service.ProductCategoryService;
import com.xiao.xiao1.service.ProductInfoService;
import com.xiao.xiao1.utils.ResultsVOUtil;
import com.xiao.xiao1.vo.ProductInfoVo;
import com.xiao.xiao1.vo.ProductVo;
import com.xiao.xiao1.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 买家端
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/list")
    public ResultVo list() {

        //1:查询所有上架的商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //2:查询类目
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //3:数据拼装
        /** 商品的类目*/
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());

            /** 商品的详情*/
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                /** 商品详情及商品类目的type是否相等*/
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultsVOUtil.success(productVoList);
    }
}
