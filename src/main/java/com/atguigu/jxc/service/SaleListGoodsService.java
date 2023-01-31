package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.SaleData;
import com.atguigu.jxc.entity.SaleListGoods;

import java.util.List;
import java.util.Map;

public interface SaleListGoodsService {
    Integer getSaleTotalByGoodsId(Integer goodsId);

    void save(SaleListGoods goods);

    Map<String, Object> listSaleGoodsById(Integer saleListId);

    void deleteBySaleListId(Integer saleListId);

    List<SaleData> getSaleDataByDay(String sTime, String eTime);

    List<SaleData> getSaleDataByMonth(String sTime, String eTime);
}
