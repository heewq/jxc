package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;

public interface GoodsTypeService {
    String loadGoodsType();

    void save(String goodsTypeName, Integer pId);

    ServiceVO deleteById(Integer goodsTypeId);
}
