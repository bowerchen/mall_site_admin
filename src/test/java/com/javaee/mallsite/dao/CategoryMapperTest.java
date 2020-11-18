package com.javaee.mallsite.dao;

import com.javaee.mallsite.MallsiteApplicationTests;
import com.javaee.mallsite.pojo.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/18 11:30
 */
public class CategoryMapperTest extends MallsiteApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void findById() {
        Category category = categoryMapper.findById(100001);
        System.out.println(category.toString());
    }

    @Test
    public void queryById() {
        Category category = categoryMapper.queryById(100002);
        System.out.println(category.toString());
    }
}
