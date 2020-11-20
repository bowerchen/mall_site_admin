package com.javaee.mallsite.enums;

import lombok.Getter;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/20 12:38
 */
@Getter
public enum RoleEnum {
    ADMIN(0),
    CUSTOMER(1);

    Integer code;

    RoleEnum(Integer code) {
        this.code = code;
    }
}

