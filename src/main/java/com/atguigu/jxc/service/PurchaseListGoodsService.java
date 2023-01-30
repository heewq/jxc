package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.PurchaseListGoods;

import java.util.Map;

public interface PurchaseListGoodsService {
    void save(PurchaseListGoods goods);

    Map<String, Object> listPurchaseGoodsById(Integer purchaseListId);

    void deleteByPurchaseListId(Integer purchaseListId);
}
