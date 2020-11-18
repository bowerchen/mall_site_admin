package com.javaee.mallsite.dao;

import com.javaee.mallsite.MallsiteApplicationTests;
import com.javaee.mallsite.pojo.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/18 13:44
 */
public class OrderMapperTest extends MallsiteApplicationTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        Order order = orderMapper.selectByPrimaryKey(1);
        System.out.println(order.toString());
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}