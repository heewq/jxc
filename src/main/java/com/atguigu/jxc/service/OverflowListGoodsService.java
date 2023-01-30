package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.OverflowListGoods;

import java.util.Map;

public interface OverflowListGoodsService {
    void save(OverflowListGoods goods);

    Map<String, Object> listOverflowGoodsById(Integer overflowListId);
}
