package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.PurchaseList;

import java.util.Map;

public interface PurchaseListService {
    void save(String purchaseNumber, PurchaseList purchaseList, String purchaseListGoodsStr);

    Map<String, Object> list(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime);

    void deleteById(Integer purchaseListId);
}
