package com.javaee.mallsite.pojo;

import lombok.Data;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/22 16:53
 */
@Data
public class Cart {

    private Integer productId;

    private Integer quantity;

    private Boolean productSelected;

    public Cart(Integer productId, Integer quantity, Boolean productSelected) {
        this.productId = productId;
        this.quantity = quantity;
        this.productSelected = productSelected;
    }
}
