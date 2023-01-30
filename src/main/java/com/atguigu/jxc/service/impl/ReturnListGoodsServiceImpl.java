package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.ReturnListGoodsDao;
import com.atguigu.jxc.entity.ReturnListGoods;
import com.atguigu.jxc.service.ReturnListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReturnListGoodsServiceImpl implements ReturnListGoodsService {
    @Autowired
    private ReturnListGoodsDao returnListGoodsDao;

    @Override
    public void save(ReturnListGoods goods) {
        returnListGoodsDao.save(goods);
    }

    @Override
    public Map<String, Object> listReturnGoodsById(Integer returnListId) {
        List<ReturnListGoods> returnListGoods = returnListGoodsDao.listReturnGoodsById(returnListId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", returnListGoods);
        return map;
    }

    @Override
    public void deleteByReturnListId(Integer returnListId) {
        returnListGoodsDao.deleteByReturnListId(returnListId);
    }
}
