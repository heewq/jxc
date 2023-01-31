package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.ReturnList;

import java.util.Map;

public interface ReturnListService {
    void save(String returnNumber, ReturnList returnList, String returnListGoodsStr);

    Map<String, Object> list(String returnNumber, Integer supplierId, Integer state, String sTime, String eTime);

    void deleteById(Integer returnListId);

    String returnStatistics(Integer goodsTypeId, String codeOrName, String sTime, String eTime);
}
