package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import com.atguigu.jxc.service.PurchaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/purchaseListGoods")
public class PurchaseListController {
    @Autowired
    private PurchaseListService purchaseListService;
    @Autowired
    private PurchaseListGoodsService purchaseListGoodsService;

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

    @PostMapping("/list")
    public Map<String, Object> listPurchaseList(@RequestParam(required = false) String purchaseNumber,
                                                @RequestParam(required = false) Integer supplierId,
                                                @RequestParam(required = false) Integer state,
                                                @RequestParam String sTime,
                                                @RequestParam String eTime) {
        return purchaseListService.list(purchaseNumber, supplierId, state, sTime, eTime);
    }

    @PostMapping("/goodsList")
    public Map<String, Object> listPurchaseGoodsById(@RequestParam Integer purchaseListId) {
        return purchaseListGoodsService.listPurchaseGoodsById(purchaseListId);
    }

    @PostMapping("/delete")
    public ServiceVO deleteById(@RequestParam Integer purchaseListId) {
        purchaseListService.deleteById(purchaseListId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/updateState")
    public ServiceVO updateState(@RequestParam Integer purchaseListId) {
        purchaseListService.updateState(purchaseListId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/count")
    public String purchaseStatistics(@RequestParam(required = false) Integer goodsTypeId,
                                     @RequestParam(required = false) String codeOrName,
                                     @RequestParam String sTime,
                                     @RequestParam String eTime) {
        return purchaseListService.purchaseStatistics(goodsTypeId, codeOrName, sTime, eTime);
    }
}
