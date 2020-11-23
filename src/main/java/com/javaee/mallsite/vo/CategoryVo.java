package com.javaee.mallsite.vo;

import lombok.Data;

import java.util.List;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/21 10:08
 */
@Data
public class CategoryVo {
    private Integer id;
    private  Integer parentId;
    private  String name;
    private  Integer sortOrder;
    private List<CategoryVo> subCategories;
}
