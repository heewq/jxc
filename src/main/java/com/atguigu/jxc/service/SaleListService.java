package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.SaleList;

import java.util.Map;

public interface SaleListService {
    void save(String saleNumber, SaleList saleList, String saleListGoodsStr);

    Map<String, Object> list(String saleNumber, Integer customerId, Integer state, String sTime, String eTime);

    void deleteById(Integer saleListId);
}
