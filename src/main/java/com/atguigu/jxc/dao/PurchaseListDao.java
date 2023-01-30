package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.PurchaseList;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseListDao {
    void save(PurchaseList purchaseList);
}
