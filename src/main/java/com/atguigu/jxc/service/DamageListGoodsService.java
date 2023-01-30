package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.DamageListGoods;

import java.util.Map;

public interface DamageListGoodsService {
    void save(DamageListGoods goods);

    Map<String, Object> listDamageGoodsById(Integer damageListId);
}
