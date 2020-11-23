package com.javaee.mallsite.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/21 18:53
 */
@Data
public class ProductVo {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private String mainImage;

    private BigDecimal price;

    private Integer status;
}
