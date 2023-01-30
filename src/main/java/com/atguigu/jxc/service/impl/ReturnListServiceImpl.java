package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.atguigu.jxc.dao.ReturnListDao;
import com.atguigu.jxc.entity.ReturnList;
import com.atguigu.jxc.entity.ReturnListGoods;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.ReturnListGoodsService;
import com.atguigu.jxc.service.ReturnListService;
import com.atguigu.jxc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnListServiceImpl implements ReturnListService {
    @Autowired
    private ReturnListDao returnListDao;
    @Autowired
    private ReturnListGoodsService returnListGoodsService;
    @Autowired
    private UserService userService;


    @Override
    public void save(String returnNumber, ReturnList returnList, String returnListGoodsStr) {
        User currentUser = userService.findByName((String) SecurityUtils.getSubject().getPrincipal());
        returnList.setUserId(currentUser.getUserId());
        returnListDao.save(returnList);
        List<ReturnListGoods> returnListGoods = JSONArray.parseArray(returnListGoodsStr, ReturnListGoods.class);
        for (ReturnListGoods goods : returnListGoods) {
            goods.setReturnListId(returnList.getReturnListId());
            returnListGoodsService.save(goods);
        }
    }
}
