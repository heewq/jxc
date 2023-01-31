package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SaleListGoodsDao;
import com.atguigu.jxc.entity.SaleData;
import com.atguigu.jxc.entity.SaleListGoods;
import com.atguigu.jxc.service.SaleListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleListGoodsServiceImpl implements SaleListGoodsService {
    @Autowired
    private SaleListGoodsDao saleListGoodsDao;

    @Override
    public Integer getSaleTotalByGoodsId(Integer goodsId) {
        return saleListGoodsDao.getSaleTotalByGoodsId(goodsId);
    }

    @Override
    public void save(SaleListGoods goods) {
        saleListGoodsDao.save(goods);
    }

    @Override
    public Map<String, Object> listSaleGoodsById(Integer saleListId) {
        List<SaleListGoods> saleListGoods = saleListGoodsDao.listSaleGoodsById(saleListId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", saleListGoods);
        return map;
    }

    @Override
    public void deleteBySaleListId(Integer saleListId) {
        saleListGoodsDao.deleteBySaleListId(saleListId);
    }

    @Override
    public List<SaleData> getSaleDataByDay(String sTime, String eTime) {
        return saleListGoodsDao.getSaleDataByDay(sTime, eTime);
    }

    @Override
    public List<SaleData> getSaleDataByMonth(String sTime, String eTime) {
        return saleListGoodsDao.getSaleDataByMonth(sTime, eTime);
    }
}
