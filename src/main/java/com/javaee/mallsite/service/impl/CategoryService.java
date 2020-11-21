package com.javaee.mallsite.service.impl;

import com.javaee.mallsite.dao.CategoryMapper;
import com.javaee.mallsite.pojo.Category;
import com.javaee.mallsite.service.ICategoryService;
import com.javaee.mallsite.vo.CategoryVo;
import com.javaee.mallsite.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.javaee.mallsite.consts.MallSiteConst.ROOT_PARENT_ID;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/21 10:11
 */
@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        List<CategoryVo> categoryVoList = new ArrayList<>();
        List<Category> categories = categoryMapper.selectAll();

        for(Category category: categories) {
            if (category.getParentId().equals(ROOT_PARENT_ID)) {
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category, categoryVo);
                categoryVoList.add(categoryVo);
            }
        }

        return ResponseVo.success(categoryVoList);
    }

}
