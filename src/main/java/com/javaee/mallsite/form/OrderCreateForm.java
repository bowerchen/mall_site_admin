package com.javaee.mallsite.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/25 21:22
 */
@Data
public class OrderCreateForm {

    @NotNull
    private Integer shippingId;
}
