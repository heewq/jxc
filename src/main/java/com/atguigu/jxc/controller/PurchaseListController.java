package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.PurchaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/purchaseListGoods")
public class PurchaseListController {
    @Autowired
    private PurchaseListService purchaseListService;

    @PostMapping("/save")
    public ServiceVO savePurchaseList(@RequestParam(required = false) String purchaseNumber,
                                      PurchaseList purchaseList,
                                      @RequestParam String purchaseListGoodsStr,
                                      HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        purchaseList.setUserId(user.getUserId());
        purchaseListService.save(purchaseNumber, purchaseList, purchaseListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }
}
