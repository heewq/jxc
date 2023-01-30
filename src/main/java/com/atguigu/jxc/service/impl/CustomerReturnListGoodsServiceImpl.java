package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.CustomerReturnListGoodsDao;
import com.atguigu.jxc.entity.CustomerReturnList;
import com.atguigu.jxc.entity.CustomerReturnListGoods;
import com.atguigu.jxc.service.CustomerReturnListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerReturnListGoodsServiceImpl implements CustomerReturnListGoodsService {

    @Autowired
    private CustomerReturnListGoodsDao customerReturnListGoodsDao;

    @Override
    public Integer getCustomerReturnTotalByGoodsId(Integer goodsId) {
        return customerReturnListGoodsDao.getCustomerReturnTotalByGoodsId(goodsId);
    }

    @Override
    public void save(CustomerReturnListGoods goods) {
        customerReturnListGoodsDao.save(goods);
    }

    @Override
    public Map<String, Object> listCustomerReturnGoodsById(Integer customerReturnListId) {
        List<CustomerReturnListGoods> customerReturnListGoods
                = customerReturnListGoodsDao.listCustomerReturnGoodsById(customerReturnListId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", customerReturnListGoods);
        return map;
    }

    @Override
    public void deleteByCustomerReturnListId(Integer customerReturnListId) {
        customerReturnListGoodsDao.deleteByCustomerReturnListId(customerReturnListId);
    }
}
