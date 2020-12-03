package com.javaee.mallsite.enums;

import lombok.Getter;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/23 15:49
 */

@Getter
public enum PaymentTypeEnum {

    PAY_ONLINE(1),
    ;
    Integer code;

    PaymentTypeEnum(Integer code) {
        this.code = code;
    }
}
