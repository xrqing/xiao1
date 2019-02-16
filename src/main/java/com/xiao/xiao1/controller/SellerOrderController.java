package com.xiao.xiao1.controller;

/*
@auther XiaoRuiQing
@Date 2019/1/27
*/


import com.xiao.xiao1.dto.OrderDto;
import com.xiao.xiao1.enums.ResultEnum;
import com.xiao.xiao1.exception.SellException;
import com.xiao.xiao1.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * 卖家端
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    /*
     * 订单列表
     * url: get /sell/seller/order/list
     * 参数：page(当前页),size（每页的数据），map集合
     * 返回：order/list
     * */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "3") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDto> orderDtoPage = orderMasterService.findList(request);
        map.put("orderDtoPage", orderDtoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("order/list", map);
    }

    /*
     * 取消订单
     * url: get /sell/seller/order/cancel
     * 参数：orderId(订单号)，map集合
     * 返回：common/error（错误页面），common/success（成功页面）
     * */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            OrderDto orderDto = orderMasterService.findOne(orderId);
            orderMasterService.cancel(orderDto);
        } catch (SellException e) {
            log.error("【卖家端取消订单】 发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success");
    }

    /*
     * 订单详情
     * url:get /sell/seller/order/detail
     * 参数：orderId(订单号) map集合
     * 返回：order/detail(查到返回详情页面)，common/error(查询不到返回错误页面)
     * */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDto orderDto = new OrderDto();
        try {
            orderDto = orderMasterService.findOne(orderId);
        } catch (SellException e) {
            log.error("【卖家端查询订单详情】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("orderDto", orderDto);
        return new ModelAndView("order/detail", map);
    }

    /*
     * 完结订单
     * url:get /sell/seller/order/finish
     * 参数：orderId(订单号) map集合
     * 返回：common/error（错误页面），common/success（成功页面）
     * */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            OrderDto orderDto = orderMasterService.findOne(orderId);
            orderMasterService.finish(orderDto);
        } catch (SellException e) {
            log.error("【卖家端完结订单】发送异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success");
    }
}
































