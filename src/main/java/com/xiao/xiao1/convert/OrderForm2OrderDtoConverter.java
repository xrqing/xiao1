package com.xiao.xiao1.convert;

/*
@auther XiaoRuiQing
@Date 2019/1/26
*/


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiao.xiao1.dto.OrderDto;
import com.xiao.xiao1.entity.OrderDetail;
import com.xiao.xiao1.enums.ResultEnum;
import com.xiao.xiao1.exception.SellException;
import com.xiao.xiao1.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/*
 * 转换器（OrderFrom -> OrderDto）
 * */
@Slf4j
public class OrderForm2OrderDtoConverter {
    public static OrderDto convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误，string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}

