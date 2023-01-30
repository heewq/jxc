package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.CustomerReturnList;

import java.util.Map;

public interface CustomerReturnListService {
    void save(String returnNumber, CustomerReturnList customerReturnList, String customerReturnListGoodsStr);

    Map<String, Object> list(String returnNumber, Integer customerId, Integer state, String sTime, String eTime);

    void deleteById(Integer customerReturnListId);
}
