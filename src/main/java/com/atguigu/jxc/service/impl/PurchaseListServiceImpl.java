package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.atguigu.jxc.dao.PurchaseListDao;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.PurchaseListGoods;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import com.atguigu.jxc.service.PurchaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseListServiceImpl implements PurchaseListService {

    @Autowired
    private PurchaseListDao purchaseListDao;
    @Autowired
    private PurchaseListGoodsService purchaseListGoodsService;

    @Override
    public void save(String purchaseNumber, PurchaseList purchaseList, String purchaseListGoodsStr) {
        purchaseListDao.save(purchaseList);
        List<PurchaseListGoods> purchaseListGoods = JSONArray.parseArray(purchaseListGoodsStr, PurchaseListGoods.class);
        for (PurchaseListGoods goods : purchaseListGoods) {
            goods.setPurchaseListId(purchaseList.getPurchaseListId());
            purchaseListGoodsService.save(goods);
        }
    }
}
