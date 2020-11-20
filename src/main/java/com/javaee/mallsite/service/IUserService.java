package com.javaee.mallsite.service;


import com.javaee.mallsite.pojo.User;
import com.javaee.mallsite.vo.ResponseVo;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/19 15:50
 */
public interface IUserService {
    /**
     * 注册
     */
    ResponseVo<User> register(User user);

    /**
     * 登录
     */
    ResponseVo<User> login(String username, String password);
}
