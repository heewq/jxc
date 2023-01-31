package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.CustomerReturnList;
import com.atguigu.jxc.service.CustomerReturnListGoodsService;
import com.atguigu.jxc.service.CustomerReturnListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/customerReturnListGoods")
public class CustomerReturnListController {
    @Autowired
    private CustomerReturnListService customerReturnListService;
    @Autowired
    private CustomerReturnListGoodsService customerReturnListGoodsService;


    @PostMapping("/save")
    public ServiceVO saveCustomerReturnList(@RequestParam(required = false) String returnNumber,
                                            CustomerReturnList customerReturnList,
                                            @RequestParam String customerReturnListGoodsStr) {
        customerReturnListService.save(returnNumber, customerReturnList, customerReturnListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/list")
    public Map<String, Object> listCustomerReturnList(@RequestParam(required = false) String returnNumber,
                                                      @RequestParam(required = false) Integer customerId,
                                                      @RequestParam(required = false) Integer state,
                                                      @RequestParam String sTime,
                                                      @RequestParam String eTime) {
        return customerReturnListService.list(returnNumber, customerId, state, sTime, eTime);
    }

    @PostMapping("/goodsList")
    public Map<String, Object> listCustomerReturnGoodsById(@RequestParam Integer customerReturnListId) {
        return customerReturnListGoodsService.listCustomerReturnGoodsById(customerReturnListId);
    }

    @PostMapping("/delete")
    public ServiceVO deleteById(@RequestParam Integer customerReturnListId) {
        customerReturnListService.deleteById(customerReturnListId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/count")
    public String customerReturnStatistics(@RequestParam(required = false) Integer goodsTypeId,
                                 @RequestParam(required = false) String codeOrName,
                                 @RequestParam String sTime,
                                 @RequestParam String eTime) {
        return customerReturnListService.customerReturnStatistics(goodsTypeId, codeOrName, sTime, eTime);
    }
}
