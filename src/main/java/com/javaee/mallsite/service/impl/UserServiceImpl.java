package com.javaee.mallsite.service.impl;

import com.javaee.mallsite.dao.UserMapper;
import com.javaee.mallsite.enums.ResponseEnum;
import com.javaee.mallsite.enums.RoleEnum;
import com.javaee.mallsite.pojo.User;
import com.javaee.mallsite.service.IUserService;
import com.javaee.mallsite.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/20 11:59
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     *
     * @param user
     */
    @Override
    public ResponseVo<User> register(User user) {

        // username不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if (countByUsername > 0) {
//            throw new RuntimeException("该账号已被注册");
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
        }

        // email不能重复
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if (countByEmail > 0) {
//            throw new RuntimeException("该邮箱已被注册");
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST);
        }

        user.setRole(RoleEnum.CUSTOMER.getCode());

        // MD5加密(Spring自带)
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));

        user.setCreateTime(new Date(System.currentTimeMillis()));

        // 写入数据库
        int resultCount = userMapper.insertSelective(user);
        if (resultCount == 0) {
//            throw new RuntimeException("注册失败，请重新注册");
            return ResponseVo.error(ResponseEnum.ERROR);

        }
        return ResponseVo.success();
    }


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResponseVo<User> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            // 用户不存在(返回 用户名或密码错误)
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        if (!user.getPassword().equalsIgnoreCase(
                DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            // 密码错误(返回 用户名或密码错误)
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        // 密码设置为空，并返回字段
        user.setPassword("");
        user.setUpdateTime(new Date(System.currentTimeMillis()));

        return ResponseVo.success(user);
    }
}
