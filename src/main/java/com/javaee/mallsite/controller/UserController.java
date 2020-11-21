package com.javaee.mallsite.controller;

import com.javaee.mallsite.enums.ResponseEnum;
import com.javaee.mallsite.form.UserLogin;
import com.javaee.mallsite.form.UserRegister;
import com.javaee.mallsite.pojo.User;
import com.javaee.mallsite.service.IUserService;
import com.javaee.mallsite.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

import static com.javaee.mallsite.consts.MallSiteConst.CURRENT_USER;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/20 13:43
 */
@RestController
//@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * json
     * @param userForm
     */
    @PostMapping("/user/register")
    public ResponseVo<User> register(@Valid @RequestBody UserRegister userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("注册提交的参数有误, {} {}",
                    Objects.requireNonNull(bindingResult.getFieldError()).getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(ResponseEnum.PARAM_ERROR, bindingResult);
        }

        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        // dto
        return userService.register(user);
    }

    /**
     * https param
     * @param
     * @RequestParam(value = "table field")
     */
    /*
    @PostMapping("/register")
    public void register(@RequestParam String username) {
        log.info("username={}", username);
    }
     */

    @PostMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLogin userLogin, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            log.error("注册提交的参数有误, {} {}",
                    Objects.requireNonNull(bindingResult.getFieldError()).getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(ResponseEnum.PARAM_ERROR, bindingResult);
        }
        ResponseVo<User> userResponseVo =  userService.login(userLogin.getUsername(), userLogin.getPassword());

        //设置Session
        session.setAttribute(CURRENT_USER, userResponseVo.getData());
        //log.info("/login sessionId={}", session.getId());

        return userResponseVo;
    }

    // session 保存在内存里，token + redis
    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession session) {
        //log.info("/user sessionId={}", session.getId());
        User user = (User) session.getAttribute(CURRENT_USER);

        return ResponseVo.success(user);
    }

    @PostMapping("/user/logout")
    public ResponseVo logout(HttpSession session) {
        log.info("/user/logout sessionId={}", session.getId());

        session.removeAttribute(CURRENT_USER);
        return ResponseVo.success();
    }
}
