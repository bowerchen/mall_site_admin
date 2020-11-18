package com.javaee.mallsite.dao;

import com.javaee.mallsite.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/17 20:24
 */
//@Mapper
public interface CategoryMapper {

    @Select("select * from mall_category where id = #{id}")
    Category findById(@Param("id") Integer id);

    Category queryById(@Param("id") Integer id);
}
