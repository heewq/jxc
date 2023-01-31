package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.SaleList;
import com.atguigu.jxc.service.SaleListGoodsService;
import com.atguigu.jxc.service.SaleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/saleListGoods")
public class SaleListController {
    @Autowired
    private SaleListService saleListService;
    @Autowired
    private SaleListGoodsService saleListGoodsService;

    @PostMapping("/save")
    public ServiceVO saveSaleList(@RequestParam(required = false) String saleNumber,
                                  SaleList saleList,
                                  @RequestParam String saleListGoodsStr) {
        saleListService.save(saleNumber, saleList, saleListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/list")
    public Map<String, Object> listSaleList(@RequestParam(required = false) String saleNumber,
                                            @RequestParam(required = false) Integer customerId,
                                            @RequestParam(required = false) Integer state,
                                            @RequestParam String sTime,
                                            @RequestParam String eTime) {
        return saleListService.list(saleNumber, customerId, state, sTime, eTime);
    }

    @PostMapping("/goodsList")
    public Map<String, Object> listSaleGoodsById(@RequestParam Integer saleListId) {
        return saleListGoodsService.listSaleGoodsById(saleListId);
    }

    @PostMapping("/delete")
    public ServiceVO deleteById(@RequestParam Integer saleListId) {
        saleListService.deleteById(saleListId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/updateState")
    public ServiceVO updateState(@RequestParam Integer saleListId) {
        saleListService.updateState(saleListId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/count")
    public String saleStatistics(@RequestParam(required = false) Integer goodsTypeId,
                                 @RequestParam(required = false) String codeOrName,
                                 @RequestParam String sTime,
                                 @RequestParam String eTime) {
        return saleListService.saleStatistics(goodsTypeId, codeOrName, sTime, eTime);
    }

    @PostMapping("/getSaleDataByDay")
    public String getSaleDataByDay(@RequestParam String sTime,
                                   @RequestParam String eTime) throws Exception {
        return saleListService.getSaleDataBy(sTime, eTime, "day");
    }

    @PostMapping("/getSaleDataByMonth")
    public String getSaleDataByMonth(@RequestParam String sTime,
                                     @RequestParam String eTime) throws Exception {
        return saleListService.getSaleDataBy(sTime, eTime, "month");
    }
}
