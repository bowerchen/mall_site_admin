package com.javaee.mallsite.controller;

import com.github.pagehelper.PageInfo;
import com.javaee.mallsite.form.OrderCreateForm;
import com.javaee.mallsite.pojo.User;
import com.javaee.mallsite.service.IOrderService;
import com.javaee.mallsite.vo.OrderVo;
import com.javaee.mallsite.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.javaee.mallsite.consts.MallSiteConst.CURRENT_USER;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/25 21:21
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/orders")
    public ResponseVo<OrderVo> create(@Valid @RequestBody OrderCreateForm form,
                                      HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER);
        return orderService.create(user.getId(), form .getShippingId());
    }

    @GetMapping("/orders")
    public ResponseVo<PageInfo> list(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER);
        return orderService.list(user.getId(), pageNum, pageSize);
    }

    @GetMapping("/orders/{orderNo}")
    public ResponseVo<OrderVo> detail(@PathVariable Long orderNo,
                                      HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER);
        return orderService.detail(user.getId(), orderNo);
    }

    @PutMapping("/orders/{orderNo}")
    public ResponseVo cancel(@PathVariable Long orderNo,
                             HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER);
        return orderService.cancel(user.getId(), orderNo);
    }
}
