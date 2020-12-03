package com.javaee.mallsite.service;

import com.github.pagehelper.PageInfo;
import com.javaee.mallsite.vo.OrderVo;
import com.javaee.mallsite.vo.ResponseVo;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/23 13:55
 */
public interface IOrderService {
    ResponseVo<OrderVo> create(Integer uid, Integer shippingId);

    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);

    ResponseVo<OrderVo> detail(Integer uid, Long orderNo);

    ResponseVo cancel(Integer uid, Long orderNo);
}
