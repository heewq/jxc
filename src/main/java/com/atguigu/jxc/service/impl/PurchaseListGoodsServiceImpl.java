package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.PurchaseListGoodsDao;
import com.atguigu.jxc.entity.PurchaseListGoods;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseListGoodsServiceImpl implements PurchaseListGoodsService {
    @Autowired
    private PurchaseListGoodsDao purchaseListGoodsDao;

    @Override
    public void save(PurchaseListGoods goods) {
        purchaseListGoodsDao.save(goods);
    }

    @Override
    public Map<String, Object> listPurchaseGoodsById(Integer purchaseListId) {
        List<PurchaseListGoods> purchaseListGoods = purchaseListGoodsDao.listPurchaseGoodsById(purchaseListId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", purchaseListGoods);
        return map;
    }

    @Override
    public void deleteByPurchaseListId(Integer purchaseListId) {
        purchaseListGoodsDao.deleteByPurchaseListId(purchaseListId);
    }
}
