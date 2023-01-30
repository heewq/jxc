package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.PurchaseListGoods;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseListGoodsDao {
    void save(PurchaseListGoods goods);
}
