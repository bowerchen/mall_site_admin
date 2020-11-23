package com.javaee.mallsite.service;

import com.javaee.mallsite.vo.CategoryVo;
import com.javaee.mallsite.vo.ResponseVo;

import java.util.List;
import java.util.Set;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/21 10:12
 */
public interface ICategoryService {

    ResponseVo<List<CategoryVo>> selectAll();

    void findSubCategoryId(Integer id, Set<Integer> resultSet);
}