package com.javaee.mallsite.service.impl;

import com.javaee.mallsite.MallsiteApplicationTests;
import com.javaee.mallsite.enums.ResponseEnum;
import com.javaee.mallsite.enums.RoleEnum;
import com.javaee.mallsite.pojo.User;
import com.javaee.mallsite.service.IUserService;
import com.javaee.mallsite.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/20 12:25
 */

@Transactional
public class UserServiceImplTest extends MallsiteApplicationTests {

    public static final String USERNAME="jack";
    public static final String PASSWORD="123456";

    @Autowired
    private IUserService userService;

    @Before
    public void register() {
        User user = new User(USERNAME, PASSWORD, "jack@qq.com", RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }

    @Test
    public void login() {
        ResponseVo<User> responseVo = userService.login(USERNAME, PASSWORD);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), ResponseVo.success().getStatus());
    }
}