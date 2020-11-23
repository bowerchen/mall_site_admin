package com.javaee.mallsite.service;

import com.github.pagehelper.PageInfo;
import com.javaee.mallsite.MallsiteApplicationTests;
import com.javaee.mallsite.enums.ResponseEnum;
import com.javaee.mallsite.form.ShippingForm;
import com.javaee.mallsite.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/22 21:46
 */
@Slf4j
public class IShippingServiceTest extends MallsiteApplicationTests {

    @Autowired
    private IShippingService shippingService;

    private Integer uid = 1;

    private ShippingForm form;

    private Integer shippingId;

    @Before
    public void before() {
        ShippingForm form = new ShippingForm();
        form.setReceiverCity("广州");
        form.setReceiverAddress("91大道");
        form.setReceiverName("bowerchen");
        form.setReceiverDistrict("白云区");
        form.setReceiverMobile("12345678901");
        form.setReceiverProvince("广东省");
        form.setReceiverZip("000000");
        form.setReceiverPhone("0750-999999");
        this.form = form;

        add();
    }

    public void add() {
        ResponseVo<Map<String, Integer>> ResponseVo = shippingService.add(uid, form);
        log.info("result={}", ResponseVo);
        this.shippingId = ResponseVo.getData().get("shippingId");
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), ResponseVo.getStatus());
    }

    @After
    public void delete() {
        ResponseVo responseVo = shippingService.delete(uid, shippingId);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void update() {
        form.setReceiverCity("深圳");
        ResponseVo responseVo = shippingService.update(uid, shippingId, form);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void list() {
        final ResponseVo<PageInfo> responseVo = shippingService.list(uid, 1, 1);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}