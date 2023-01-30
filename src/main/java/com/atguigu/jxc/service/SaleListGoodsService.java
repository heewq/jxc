package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.SaleListGoods;

import java.util.Map;

public interface SaleListGoodsService {
    Integer getSaleTotalByGoodsId(Integer goodsId);

    void save(SaleListGoods goods);

    Map<String, Object> listSaleGoodsById(Integer saleListId);

    void deleteBySaleListId(Integer saleListId);
}
