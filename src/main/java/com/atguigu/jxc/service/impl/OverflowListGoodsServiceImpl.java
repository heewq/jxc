package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.OverflowListGoodsDao;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.service.OverflowListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OverflowListGoodsServiceImpl implements OverflowListGoodsService {
    @Autowired
    private OverflowListGoodsDao overflowListGoodsDao;

    @Override
    public void save(OverflowListGoods goods) {
        overflowListGoodsDao.save(goods);
    }

    @Override
    public Map<String, Object> listOverflowGoodsById(Integer overflowListId) {
        List<OverflowListGoods> overflowListGoods = overflowListGoodsDao.listOverflowGoodsById(overflowListId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", overflowListGoods);
        return map;
    }
}
