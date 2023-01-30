package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.OverflowList;

import java.util.Map;

public interface OverflowListService {
    void save(String overflowNumber, OverflowList overflowList, String overflowListGoodsStr);

    Map<String, Object> list(String sTime, String eTime);
}
