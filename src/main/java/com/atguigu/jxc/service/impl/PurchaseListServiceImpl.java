package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.atguigu.jxc.dao.PurchaseListDao;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.PurchaseListGoods;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import com.atguigu.jxc.service.PurchaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseListServiceImpl implements PurchaseListService {

    @Autowired
    private PurchaseListDao purchaseListDao;
    @Autowired
    private PurchaseListGoodsService purchaseListGoodsService;
    @Autowired
    private GoodsService goodsService;

    @Override
    public void save(String purchaseNumber, PurchaseList purchaseList, String purchaseListGoodsStr) {
        purchaseListDao.save(purchaseList);
        List<PurchaseListGoods> purchaseListGoods = JSONArray.parseArray(purchaseListGoodsStr, PurchaseListGoods.class);
        for (PurchaseListGoods goods : purchaseListGoods) {
            goods.setPurchaseListId(purchaseList.getPurchaseListId());
            purchaseListGoodsService.save(goods);
            goodsService.updateInventoryQuantityById(goods.getGoodsId(), goods.getGoodsNum());
        }
    }

    @Override
    public Map<String, Object> list(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime) {
        List<PurchaseList> purchaseLists = purchaseListDao.list(purchaseNumber, supplierId, state, sTime, eTime);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", purchaseLists);
        return map;
    }

    @Override
    public void deleteById(Integer purchaseListId) {
        purchaseListGoodsService.deleteByPurchaseListId(purchaseListId);
        purchaseListDao.deleteById(purchaseListId);
    }
}
