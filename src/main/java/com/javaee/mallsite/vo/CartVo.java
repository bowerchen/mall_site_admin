package com.javaee.mallsite.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/22 14:20
 *
 * 购物车
 */
@Data
public class CartVo {

    private List<CartProductVo> cartProductVoList;

    private Boolean selectedAll;

    private BigDecimal cartTotalPrice;

    private Integer cartTotalQuantity;
}
