package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.DamageListGoodsDao;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.service.DamageListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DamageListGoodsServiceImpl implements DamageListGoodsService {
    @Autowired
    private DamageListGoodsDao damageListGoodsDao;

    @Override
    public void save(DamageListGoods goods) {
        damageListGoodsDao.save(goods);
    }

    @Override
    public Map<String, Object> listDamageGoodsById(Integer damageListId) {
        List<DamageListGoods> damageListGoods = damageListGoodsDao.listDamageGoodsById(damageListId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", damageListGoods);
        return map;
    }
}
