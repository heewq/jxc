package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.atguigu.jxc.dao.SaleListDao;
import com.atguigu.jxc.domain.SaleAndReturnStatistics;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.*;
import com.atguigu.jxc.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            Goods stockGoods = goodsService.getById(goods.getGoodsId());
            stockGoods.setState(2);
            stockGoods.setInventoryQuantity(stockGoods.getInventoryQuantity() - goods.getGoodsNum());
            goodsService.update(stockGoods);
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

    @Override
    public void updateState(Integer saleListId) {
        saleListDao.updateState(saleListId);
    }

    @Override
    public String saleStatistics(Integer goodsTypeId, String codeOrName, String sTime, String eTime) {
        List<SaleAndReturnStatistics> saleAndReturnStatistics
                = saleListDao.saleStatistics(goodsTypeId, codeOrName, sTime, eTime);
        return JSON.toJSONString(saleAndReturnStatistics);
    }

    @Override
    public String getSaleDataBy(String sTime, String eTime, String date) throws Exception {
        List<SaleData> sales = new ArrayList<>();
        List<SaleData> saleDataList;
        List<String> timeSlot;
        switch (date) {
            case "day":
                saleDataList = saleListGoodsService.getSaleDataByDay(sTime, eTime);
                timeSlot = DateUtil.getTimeSlotByDay(sTime, eTime);
                break;
            case "month":
                saleDataList = saleListGoodsService.getSaleDataByMonth(sTime, eTime);
                timeSlot = DateUtil.getTimeSlotByMonth(sTime, eTime);
                break;
            default:
                throw new IllegalArgumentException();
        }
        for (String time : timeSlot) {
            boolean hasData = false;
            SaleData data = null;
            for (SaleData saleData : saleDataList) {
                if (time.equals(saleData.getDate())) {
                    data = saleData;
                    hasData = true;
                    break;
                }
            }
            if (!hasData) {
                data = new SaleData();
                data.setDate(time);
                data.setSaleTotal(0D);
                data.setPurchasingTotal(0D);
                data.setProfit(0D);
            }
            sales.add(data);
        }
        return JSON.toJSONString(sales);
    }
}
