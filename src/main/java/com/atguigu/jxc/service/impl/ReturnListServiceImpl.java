package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPObject;
import com.atguigu.jxc.dao.ReturnListDao;
import com.atguigu.jxc.domain.PurchaseAndReturnStatistics;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.ReturnList;
import com.atguigu.jxc.entity.ReturnListGoods;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.ReturnListGoodsService;
import com.atguigu.jxc.service.ReturnListService;
import com.atguigu.jxc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReturnListServiceImpl implements ReturnListService {
    @Autowired
    private ReturnListDao returnListDao;
    @Autowired
    private ReturnListGoodsService returnListGoodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;

    @Override
    public void save(String returnNumber, ReturnList returnList, String returnListGoodsStr) {
        User currentUser = userService.findByName((String) SecurityUtils.getSubject().getPrincipal());
        returnList.setUserId(currentUser.getUserId());
        returnListDao.save(returnList);
        List<ReturnListGoods> returnListGoods = JSONArray.parseArray(returnListGoodsStr, ReturnListGoods.class);
        for (ReturnListGoods goods : returnListGoods) {
            goods.setReturnListId(returnList.getReturnListId());
            returnListGoodsService.save(goods);
            Goods stockGoods = goodsService.getById(goods.getGoodsId());
            stockGoods.setState(2);
            stockGoods.setInventoryQuantity(stockGoods.getInventoryQuantity() - goods.getGoodsNum());
            goodsService.update(stockGoods);
        }
    }

    @Override
    public Map<String, Object> list(String returnNumber, Integer supplierId, Integer state, String sTime, String eTime) {
        List<ReturnList> returnLists = returnListDao.list(returnNumber, supplierId, state, sTime, eTime);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", returnLists);
        return map;
    }

    @Override
    public void deleteById(Integer returnListId) {
        returnListGoodsService.deleteByReturnListId(returnListId);
        returnListDao.deleteById(returnListId);
    }

    @Override
    public String returnStatistics(Integer goodsTypeId, String codeOrName, String sTime, String eTime) {
        List<PurchaseAndReturnStatistics> purchaseAndReturnStatistics
                = returnListDao.returnStatistics(goodsTypeId, codeOrName, sTime, eTime);
        return JSON.toJSONString(purchaseAndReturnStatistics);
    }
}
