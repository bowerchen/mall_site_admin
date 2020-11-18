package com.javaee.mallsite;

import com.javaee.mallsite.dao.CategoryMapper;
import com.javaee.mallsite.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.javaee.mallsite.dao")
public class MallsiteApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void contextLoads() {
        Category category = categoryMapper.findById(100001);
        System.out.println(category.toString());
    }

}
