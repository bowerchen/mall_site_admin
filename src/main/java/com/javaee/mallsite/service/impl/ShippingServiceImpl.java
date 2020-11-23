package com.javaee.mallsite.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaee.mallsite.dao.ShippingMapper;
import com.javaee.mallsite.enums.ResponseEnum;
import com.javaee.mallsite.form.ShippingForm;
import com.javaee.mallsite.pojo.Shipping;
import com.javaee.mallsite.service.IShippingService;
import com.javaee.mallsite.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javaee.mallsite.enums.ResponseEnum.DELETE_SHIPPING_FAIL;
import static com.javaee.mallsite.enums.ResponseEnum.ERROR;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/22 21:37
 */
@Service
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        shipping.setUserId(uid);
        shipping.setCreateTime(new Date(System.currentTimeMillis()));
        final int row = shippingMapper.insertSelective(shipping);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("shippingId", shipping.getId());

        return ResponseVo.success(map);
    }

    @Override
    public ResponseVo delete(Integer uid, Integer shippingId) {
        final int row = shippingMapper.deleteByIdAndUid(uid, shippingId);
        if (row == 0 ) {
            return ResponseVo.error(DELETE_SHIPPING_FAIL);
        }

        return ResponseVo.success();
    }

    @Override
    public ResponseVo update(Integer uid, Integer shippingId, ShippingForm form) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        shipping.setUserId(uid);
        shipping.setId(shippingId);
        shipping.setUpdateTime(new Date(System.currentTimeMillis()));
        final int row = shippingMapper.updateByPrimaryKeySelective(shipping);

        if (row == 0) {
            return ResponseVo.error(ERROR);
        }
        return ResponseVo.success();
    }

    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        final List<Shipping> shippings = shippingMapper.selectByUid(uid);
        PageInfo pageInfo = new PageInfo(shippings);
        return ResponseVo.success(pageInfo);
    }
}
