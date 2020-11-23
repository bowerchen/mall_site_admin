package com.javaee.mallsite.enums;

import lombok.Getter;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/22 13:06
 */
@Getter
public enum ProductStatusEnum {

    ON_SALE(1),
    OFF_SALE(2),
    DELETE(3),
    ;

    Integer code;

    ProductStatusEnum(Integer code) {
        this.code = code;
    }
}
