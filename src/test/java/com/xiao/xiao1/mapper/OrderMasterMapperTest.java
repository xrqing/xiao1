package com.xiao.xiao1.mapper;

import com.xiao.xiao1.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/*
@auther XiaoRuiQing
@Date 2019/1/24
*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterMapperTest {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    /**
     * 微信号设置
     */
    private final String OPENID = "11111";

    /**
     * 插入数据
     */
    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("134568");
        orderMaster.setBuyerName("赵六");
        orderMaster.setBuyerPhone("13760389469");
        orderMaster.setBuyerAddress("江西玉都");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(3.5));
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        OrderMaster result = orderMasterMapper.save(orderMaster);
        Assert.assertNotNull(result);
        System.out.println("插入的数据为:" + result);
    }

    /**
     * 根据买家的openID来查并展示分页
     */
    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0, 1);
        Page<OrderMaster> result = orderMasterMapper.findByBuyerOpenid(OPENID, request);
        Assert.assertNotEquals(0, result.getTotalElements());
        System.out.println("分页:" + result);
    }
}