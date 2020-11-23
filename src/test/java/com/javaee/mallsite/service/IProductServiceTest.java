package com.javaee.mallsite.service;

import com.github.pagehelper.PageInfo;
import com.javaee.mallsite.MallsiteApplicationTests;
import com.javaee.mallsite.enums.ResponseEnum;
import com.javaee.mallsite.vo.ProductDetailVo;
import com.javaee.mallsite.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/21 19:16
 */
public class IProductServiceTest extends MallsiteApplicationTests {

    @Autowired
    private IProductService productService;

    @Test
    public void list() {
        ResponseVo<PageInfo> responseVo = productService.list(null, 1, 2);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void detail() {
        ResponseVo<ProductDetailVo> responseVo = productService.detail(26);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

}