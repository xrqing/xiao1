package com.xiao.xiao1.controller;

/*
@auther XiaoRuiQing
@Date 2019/1/29
*/

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xiao.xiao1.entity.ProductCategory;
import com.xiao.xiao1.exception.SellException;
import com.xiao.xiao1.form.CategoryForm;
import com.xiao.xiao1.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/*
 * 卖家端商品类目
 * */
@Controller
@RequestMapping("/seller/category")
public class SellCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /*
     * 商品类目列表
     * url:get  /sell/seller/product/list
     * 参数： page,size,map
     * 返回：category/list
     * */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "3") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductCategory> productCategoryPage = productCategoryService.findAll(request);
        map.put("productCategoryPage", productCategoryPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("category/list", map);
    }

    /*
     * 卖家端商品类别新增及修改页面
     * url:get /sell/seller/category/add
     * 参数：categoryId map
     * 返回：category/add(返回添加页面)
     * */
    @GetMapping("/add")
    public ModelAndView add(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                            Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory productCategory = productCategoryService.findOne(categoryId);
            map.put("category", productCategory);
        }
        return new ModelAndView("category/add", map);
    }

    /*
     * 卖家端商品类别新增及修改方法
     * url:get /sell/seller/category/save
     * 参数：Categoryform map,BindingResult
     * 返回：common/success(返回正确页面)，common/error(返回错误页面)
     * */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/add");
            return new ModelAndView("common/error", map);
        }
        ProductCategory productCategory = new ProductCategory();
        try {
            if (form.getCategoryId() != null) {
                productCategory = productCategoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, productCategory);
            productCategoryService.save(productCategory);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/add");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}

























