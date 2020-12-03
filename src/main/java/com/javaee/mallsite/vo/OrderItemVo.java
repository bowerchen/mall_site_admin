package com.javaee.mallsite.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/23 13:48
 */
@Data
public class OrderItemVo {
    private Long orderNo;

    private Integer productId;

    private String productName;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Date createTime;
}
