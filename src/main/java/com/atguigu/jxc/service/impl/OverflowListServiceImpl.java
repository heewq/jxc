package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.atguigu.jxc.dao.OverflowListDao;
import com.atguigu.jxc.dao.OverflowListGoodsDao;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.service.OverflowListGoodsService;
import com.atguigu.jxc.service.OverflowListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OverflowListServiceImpl implements OverflowListService {
    @Autowired
    private OverflowListDao overflowListDao;
    @Autowired
    private OverflowListGoodsService overflowListGoodsService;

    @Override
    public void save(String overflowNumber, OverflowList overflowList, String overflowListGoodsStr) {
        overflowListDao.save(overflowList);
        List<OverflowListGoods> overflowListGoods = JSONArray.parseArray(overflowListGoodsStr, OverflowListGoods.class);
        for (OverflowListGoods goods : overflowListGoods) {
            goods.setOverflowListId(overflowList.getOverflowListId());
            overflowListGoodsService.save(goods);
        }
    }

    @Override
    public Map<String, Object> list(String sTime, String eTime) {
        List<OverflowList> overflowLists = overflowListDao.list(sTime, eTime);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", overflowLists);

        return map;
    }
}
