package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.PurchaseListGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseListGoodsDao {
    void save(PurchaseListGoods goods);

    List<PurchaseListGoods> listPurchaseGoodsById(@Param("purchaseListId") Integer purchaseListId);

    void deleteByPurchaseListId(@Param("purchaseListId") Integer purchaseListId);
}
