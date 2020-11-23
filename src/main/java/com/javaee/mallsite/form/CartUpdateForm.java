package com.javaee.mallsite.form;

import lombok.Data;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/22 19:41
 */
@Data
public class CartUpdateForm {

    private Integer quantity;

    private Boolean selected;
}
