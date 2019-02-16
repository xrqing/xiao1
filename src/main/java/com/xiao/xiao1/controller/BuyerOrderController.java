package com.xiao.xiao1.controller;

/*
@auther XiaoRuiQing
@Date 2019/1/26
*/

import com.xiao.xiao1.convert.OrderForm2OrderDtoConverter;
import com.xiao.xiao1.dto.OrderDto;
import com.xiao.xiao1.enums.ResultEnum;
import com.xiao.xiao1.exception.SellException;
import com.xiao.xiao1.form.OrderForm;
import com.xiao.xiao1.service.BuyerService;
import com.xiao.xiao1.service.OrderMasterService;
import com.xiao.xiao1.utils.ResultsVOUtil;
import com.xiao.xiao1.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家端
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderForm2OrderDtoConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【创建订单】 购物车不为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto createResult = orderMasterService.create(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultsVOUtil.success(map);
    }

    /**
     * 订单列表
     */
    @GetMapping("/list")
    public ResultVo<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDto> orderDtoPage = orderMasterService.findList(openid, request);
        return ResultsVOUtil.success(orderDtoPage.getContent());
    }

    /**
     * 订单详情
     */
    @GetMapping("/detail")
    public ResultVo<OrderDto> detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        OrderDto orderDto = buyerService.findOrderone(openid, orderId);
        return ResultsVOUtil.success(orderDto);
    }

    /**
     * 取消订单
     */
    @PostMapping("/cancel")
    public ResultVo<OrderDto> cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultsVOUtil.success();
    }
}


















































