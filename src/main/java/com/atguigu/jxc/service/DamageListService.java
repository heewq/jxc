package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.DamageList;

import java.io.IOException;
import java.util.Map;

public interface DamageListService {
    void save(String damageNumber, DamageList damageList, String damageListGoodsStr) throws IOException;

    Map<String, Object> list(String sTime, String eTime);
}
