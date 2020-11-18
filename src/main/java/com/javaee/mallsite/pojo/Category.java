package com.javaee.mallsite.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/17 20:13
 */
@Data
public class Category {
    private Integer id;
    private  Integer parentId;
    private  String name;
    private  Integer status;
    private  Integer sortOrder;
    private Date createTime;
    private  Date updateTime;
}
