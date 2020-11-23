package com.javaee.mallsite.service;

import com.github.pagehelper.PageInfo;
import com.javaee.mallsite.form.ShippingForm;
import com.javaee.mallsite.vo.ResponseVo;

import java.util.Map;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/22 21:33
 */
public interface IShippingService {
    ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form);

    ResponseVo delete(Integer uid, Integer shippingId);

    ResponseVo update(Integer uid, Integer shippingId, ShippingForm form);

    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);

}
