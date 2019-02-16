package com.xiao.xiao1.convert;
/*
@auther XiaoRuiQing
@Date 2019/1/25
*/

import com.xiao.xiao1.dto.OrderDto;
import com.xiao.xiao1.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/*
 * 转换器(OrderMaster -> OrderDto )
 * */
public class OrderMaster2OrderDtoConverter {

    public static OrderDto convert(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
