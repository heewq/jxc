package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.ReturnListGoods;

import java.util.Map;

public interface ReturnListGoodsService {
    void save(ReturnListGoods goods);

    Map<String, Object> listReturnGoodsById(Integer returnListId);

    void deleteByReturnListId(Integer returnListId);
}
