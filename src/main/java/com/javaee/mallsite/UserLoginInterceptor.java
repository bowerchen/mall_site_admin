package com.javaee.mallsite;

import com.javaee.mallsite.exception.UserLoginException;
import com.javaee.mallsite.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.javaee.mallsite.consts.MallSiteConst.CURRENT_USER;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/20 20:49
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * true 表示继续流程，false表示中断
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("perHandle...");
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        if (user == null) {
            log.info("user=null");
            throw new UserLoginException();
//            return false;
            //return ResponseVo.error(ResponseEnum.NEED_LOGIN);
        }
        return true;
    }
}
