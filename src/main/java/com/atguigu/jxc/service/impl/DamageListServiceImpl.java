package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.atguigu.jxc.dao.DamageListDao;
import com.atguigu.jxc.dao.DamageListGoodsDao;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.service.DamageListGoodsService;
import com.atguigu.jxc.service.DamageListService;
import com.atguigu.jxc.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DamageListServiceImpl implements DamageListService {
    @Autowired
    private DamageListDao damageListDao;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private DamageListGoodsService damageListGoodsService;

    @Override
    public void save(String damageNumber, DamageList damageList, String damageListGoodsStr) throws IOException {
        damageListDao.save(damageList);
//  [{"goodsId":12,"goodsTypeId":10,"goodsCode":"0004","goodsName":"新疆红枣","goodsModel":"2斤装","goodsUnit":"袋","lastPurchasingPrice":13,"price":"13","goodsNum":"1","total":13}]
        List<DamageListGoods> damageListGoods = JSONArray.parseArray(damageListGoodsStr, DamageListGoods.class);
        for (DamageListGoods goods : damageListGoods) {
            goods.setDamageListId(damageList.getDamageListId());
            damageListGoodsService.save(goods);
            goodsService.updateInventoryQuantityById(goods.getGoodsId(), goods.getGoodsNum() * -1);
        }
    }

    @Override
    public Map<String, Object> list(String sTime, String eTime) {
        List<DamageList> damageLists = damageListDao.list(sTime, eTime);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", damageLists);

        return map;
    }
}
