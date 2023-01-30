package com.atguigu.jxc.service;

public interface GoodsTypeService {
    String loadGoodsType();

    void save(String goodsTypeName, Integer pId);

    void deleteById(Integer goodsTypeId);
}
