package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Goods;

import java.util.Map;

public interface GoodsService {
    Map<String, Object> stockSearch(Integer page,
                                    Integer rows,
                                    String codeOrName,
                                    Integer goodsTypeId);

    Map<String, Object> listGoods(Integer page, Integer rows, String goodsName, Integer goodsTypeId);

    void insert(Goods goods);

    void update(Goods goods);

    String getCode();

    ServiceVO delete(Integer goodsId);

    Map<String, Object> listNoInventoryQuantity(Integer page, Integer rows, String nameOrCode);

    Map<String, Object> listHasInventoryQuantity(Integer page, Integer rows, String nameOrCode);

    void updateById(Integer goodsId, Integer inventoryQuantity, double purchasingPrice);

    Map<String, Object> listAlarm();

    void updateInventoryQuantityById(Integer goodsId, Integer quantity);

    ServiceVO deleteStock(Integer goodsId);

    Goods getById(Integer goodsId);
}
