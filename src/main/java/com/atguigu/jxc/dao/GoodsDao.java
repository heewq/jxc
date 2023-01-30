package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDao {
    List<Goods> listInventory(Integer offset, Integer rows, String codeOrName, Integer goodsTypeId);

    Integer getGoodsInventoryCount(@Param("codeOrName") String codeOrName, @Param("goodsTypeId") Integer goodsTypeId);

    void insert(@Param("goods") Goods goods);

    void update(@Param("goods") Goods goods);

    String getMaxCode();

    void deleteById(@Param("goodsId") Integer goodsId);

    List<Goods> listNoInventoryQuantity(@Param("offset") Integer offset, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode);

    Integer totalNoInventoryQuantity(@Param("nameOrCode") String nameOrCode);

    List<Goods> listHasInventoryQuantity(@Param("offset") Integer offset, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode);

    Integer totalHasInventoryQuantity(@Param("nameOrCode") String nameOrCode);

    void updateById(@Param("goodsId") Integer goodsId, @Param("inventoryQuantity") Integer inventoryQuantity, @Param("purchasingPrice") double purchasingPrice);

    List<Goods> listAlarm();

//    List<Goods> listGoods(@Param("offset") Integer offset, @Param("rows") Integer rows, @Param("goodsName") String goodsName, @Param("goodsTypeId") Integer goodsTypeId);

//    Integer getTotalGoods(@Param("goodsName") String goodsName, @Param("goodsTypeId") Integer goodsTypeId);
}
