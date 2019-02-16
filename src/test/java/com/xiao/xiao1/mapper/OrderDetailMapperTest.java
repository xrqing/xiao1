package com.xiao.xiao1.mapper;

import com.xiao.xiao1.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/*
@auther XiaoRuiQing
@Date 2019/1/24
*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailMapperTest {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    /**
     * 插入数据
     */
    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("111113");
        orderDetail.setOrderId("11123");
        orderDetail.setProductId("111234");
        orderDetail.setProductQuantity(2);
        orderDetail.setProductPrice(new BigDecimal(1.5));
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductIcon("http://xxxx1x.jpg");
        orderDetail.setCreateTime(new Date());
        orderDetail.setUpdateTime(new Date());
        OrderDetail result = orderDetailMapper.save(orderDetail);
        Assert.assertNotNull(result);
        System.out.println("插入的数据为:" + result);
    }

    /**
     * 根据订单号去查询订单详情
     */
    @Test
    public void findByOrderId() {
        List<OrderDetail> result = orderDetailMapper.findByOrderId("11121");
        Assert.assertNotEquals(0, result.size());
        System.out.println("查出的订单详情为：" + result);
    }
}