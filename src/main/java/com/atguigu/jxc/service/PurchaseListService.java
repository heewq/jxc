package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.PurchaseList;

public interface PurchaseListService {
    void save(String purchaseNumber, PurchaseList purchaseList, String purchaseListGoodsStr);
}
