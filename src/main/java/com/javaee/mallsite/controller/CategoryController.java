package com.javaee.mallsite.controller;

import com.javaee.mallsite.service.ICategoryService;
import com.javaee.mallsite.vo.CategoryVo;
import com.javaee.mallsite.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/21 10:32
 */
@RestController
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponseVo<List<CategoryVo>> selectAll() {

        return categoryService.selectAll();
    }
}
