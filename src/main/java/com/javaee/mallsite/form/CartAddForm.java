package com.javaee.mallsite.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/22 14:37
 *
 * 添加商品
 */
@Data
public class CartAddForm {

    @NotNull
    private Integer productId;

    private Boolean selected = true;
}
