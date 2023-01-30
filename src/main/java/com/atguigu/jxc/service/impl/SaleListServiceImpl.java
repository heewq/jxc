package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.atguigu.jxc.dao.SaleListDao;
import com.atguigu.jxc.entity.SaleList;
import com.atguigu.jxc.entity.SaleListGoods;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.SaleListGoodsService;
import com.atguigu.jxc.service.SaleListService;
import com.atguigu.jxc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleListServiceImpl implements SaleListService {
    @Autowired
    private SaleListDao saleListDao;
    @Autowired
    private SaleListGoodsService saleListGoodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;

    @Override
    public void save(String saleNumber, SaleList saleList, String saleListGoodsStr) {
        User currentUser = userService.findByName((String) SecurityUtils.getSubject().getPrincipal());
        saleList.setUserId(currentUser.getUserId());
        saleListDao.save(saleList);
        List<SaleListGoods> saleListGoods = JSONArray.parseArray(saleListGoodsStr, SaleListGoods.class);
        for (SaleListGoods goods : saleListGoods) {
            goods.setSaleListId(saleList.getSaleListId());
            saleListGoodsService.save(goods);
            goodsService.updateInventoryQuantityById(goods.getGoodsId(), goods.getGoodsNum() * -1);
        }
    }

    @Override
    public Map<String, Object> list(String saleNumber, Integer customerId, Integer state, String sTime, String eTime) {
        List<SaleList> saleLists = saleListDao.list(saleNumber, customerId, state, sTime, eTime);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", saleLists);
        return map;
    }

    @Override
    public void deleteById(Integer saleListId) {
        saleListGoodsService.deleteBySaleListId(saleListId);
        saleListDao.deleteById(saleListId);
    }
}
