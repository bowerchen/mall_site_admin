package com.javaee.mallsite.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/22 21:31
 */
@Data
public class ShippingForm {

    @NotBlank()
    private String receiverName;

    @NotBlank()
    private String receiverPhone;

    @NotBlank()
    private String receiverMobile;

    @NotBlank()
    private String receiverProvince;

    @NotBlank()
    private String receiverCity;

    @NotBlank()
    private String receiverDistrict;

    @NotBlank()
    private String receiverAddress;

    @NotBlank()
    private String receiverZip;
}

