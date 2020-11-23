package com.javaee.mallsite.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/22 12:58
 */
@Data
public class ProductDetailVo {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private String mainImage;

    private String subImages;

    private String detail;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
