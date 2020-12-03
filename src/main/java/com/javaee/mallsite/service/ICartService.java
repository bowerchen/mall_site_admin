package com.javaee.mallsite.service;

import com.javaee.mallsite.form.CartAddForm;
import com.javaee.mallsite.form.CartUpdateForm;
import com.javaee.mallsite.pojo.Cart;
import com.javaee.mallsite.vo.CartVo;
import com.javaee.mallsite.vo.ResponseVo;

import java.util.List;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/22 16:30
 */
public interface ICartService {

    ResponseVo<CartVo> add(Integer uid, CartAddForm form);

    ResponseVo<CartVo> list(Integer uid);

    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm form);

    ResponseVo<CartVo> delete(Integer uid, Integer productId);

    ResponseVo<CartVo> selectAll(Integer uid);

    ResponseVo<CartVo> unSelectAll(Integer uid);

    ResponseVo<Integer> sum(Integer uid);

    List<Cart> listForCart(Integer uid);
}
