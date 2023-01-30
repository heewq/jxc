package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SaleListGoodsDao;
import com.atguigu.jxc.service.SaleListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleListGoodsServiceImpl implements SaleListGoodsService {
    @Autowired
    private SaleListGoodsDao saleListGoodsDao;

    @Override
    public Integer getSaleTotalByGoodsId(Integer goodsId) {
        return saleListGoodsDao.getSaleTotalByGoodsId(goodsId);
    }
}
