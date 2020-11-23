package com.javaee.mallsite.service;

import com.github.pagehelper.PageInfo;
import com.javaee.mallsite.vo.ProductDetailVo;
import com.javaee.mallsite.vo.ResponseVo;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/21 19:02
 */
public interface IProductService {

    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);

    ResponseVo<ProductDetailVo> detail(Integer productId);
}
