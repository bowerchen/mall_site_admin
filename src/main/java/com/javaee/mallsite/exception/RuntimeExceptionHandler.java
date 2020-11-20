package com.javaee.mallsite.exception;

import com.javaee.mallsite.enums.ResponseEnum;
import com.javaee.mallsite.vo.ResponseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/20 16:15
 */
@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
//    @ResponseStatus(HttpStatus.ACCEPTED)  返回状态码
    public ResponseVo handle(RuntimeException e) {
        return ResponseVo.error(ResponseEnum.ERROR, e.getMessage());
    }

    @ExceptionHandler(UserLoginException.class)
    @ResponseBody
    public ResponseVo userLoginHandler() {
        return ResponseVo.error(ResponseEnum.NEED_LOGIN);
    }


}
