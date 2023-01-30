package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.atguigu.jxc.dao.CustomerReturnListDao;
import com.atguigu.jxc.entity.CustomerReturnList;
import com.atguigu.jxc.entity.CustomerReturnListGoods;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.CustomerReturnListGoodsService;
import com.atguigu.jxc.service.CustomerReturnListService;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerReturnListServiceImpl implements CustomerReturnListService {
    @Autowired
    private CustomerReturnListDao customerReturnListDao;
    @Autowired
    private CustomerReturnListGoodsService customerReturnListGoodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;

    @Override
    public void save(String returnNumber, CustomerReturnList customerReturnList, String customerReturnListGoodsStr) {
        User currentUser = userService.findByName(((String) SecurityUtils.getSubject().getPrincipal()));
        customerReturnList.setUserId(currentUser.getUserId());
        customerReturnListDao.save(customerReturnList);
        List<CustomerReturnListGoods> customerReturnListGoods = JSONArray.parseArray(customerReturnListGoodsStr, CustomerReturnListGoods.class);
        for (CustomerReturnListGoods goods : customerReturnListGoods) {
            goods.setCustomerReturnListId(customerReturnList.getCustomerReturnListId());
            customerReturnListGoodsService.save(goods);
            goodsService.updateInventoryQuantityById(goods.getGoodsId(), goods.getGoodsNum());
        }
    }

    @Override
    public Map<String, Object> list(String returnNumber, Integer customerId, Integer state, String sTime, String eTime) {
        List<CustomerReturnList> customerReturnLists = customerReturnListDao.list(returnNumber, customerId, state, sTime, eTime);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", customerReturnLists);
        return map;
    }

    @Override
    public void deleteById(Integer customerReturnListId) {
        customerReturnListGoodsService.deleteByCustomerReturnListId(customerReturnListId);
        customerReturnListDao.deleteById(customerReturnListId);
    }
}
