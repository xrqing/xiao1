package com.xiao.xiao1.controller;

/*
@auther XiaoRuiQing
@Date 2019/1/28
*/

import com.xiao.xiao1.entity.ProductCategory;
import com.xiao.xiao1.entity.ProductInfo;
import com.xiao.xiao1.exception.SellException;
import com.xiao.xiao1.form.ProductForm;
import com.xiao.xiao1.service.ProductCategoryService;
import com.xiao.xiao1.service.ProductInfoService;
import com.xiao.xiao1.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /*
     * 卖家端商品列表
     * url:get  /sell/seller/product/list
     * 参数： page,size,map
     * 返回：product/list
     * */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "3") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    /*
     * 卖家端商品上架
     * url:get /sell/seller/product/on_sale
     * 参数：productId map
     * 返回：common/success(返回正确页面)，common/error(返回错误页面)
     * */
    @GetMapping("/on_sale")
    public ModelAndView on_sale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productInfoService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
//        map.put("msg", ResultEnum.PRODUCT_ONSALE_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /*
     * 卖家端商品下架
     * url:get /sell/seller/product/off_sale
     * 参数：productId map
     * 返回：common/success(返回正确页面)，common/error(返回错误页面)
     * */
    @GetMapping("/off_sale")
    public ModelAndView off_sale(@RequestParam("productId") String productId,
                                 Map<String, Object> map) {
        try {
            productInfoService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
//        map.put("msg",ResultEnum.PRODUCT_ODDSALE_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /*
     * 卖家端商品新增及修改页面
     * url:get /sell/seller/product/add
     * 参数：productId map
     * 返回：product/add(返回添加页面)
     * */
    @GetMapping("/add")
    public ModelAndView add(@RequestParam(value = "productId", required = false) String productId,
                            Map<String, Object> map) {
        /** 判断productId是否为空 */
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo", productInfo);
        }
        /** 查询类目 */
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList", productCategoryList);
        return new ModelAndView("product/add", map);
    }

    /*
     * 卖家端商品新增及修改方法
     * url:post /sell/seller/product/save
     * 参数：Productform map,BindingResult
     * 返回：common/success(返回正确页面)，common/error(返回错误页面)
     * */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/add");
            return new ModelAndView("common/error", map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            /** 如果productId为空，说明为新增 */
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productInfoService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productInfoService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/add");
            return new ModelAndView("common/error", map);
        }
//        map.put("msg",ResultEnum.product_update_success.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}



















































