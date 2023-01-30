package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.CustomerReturnListGoods;

import java.util.Map;

public interface CustomerReturnListGoodsService {
    Integer getCustomerReturnTotalByGoodsId(Integer goodsId);

    void save(CustomerReturnListGoods goods);

    Map<String, Object> listCustomerReturnGoodsById(Integer customerReturnListId);

    void deleteByCustomerReturnListId(Integer customerReturnListId);
}
