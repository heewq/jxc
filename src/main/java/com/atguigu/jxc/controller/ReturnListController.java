package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.ReturnList;
import com.atguigu.jxc.service.ReturnListGoodsService;
import com.atguigu.jxc.service.ReturnListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/returnListGoods")
public class ReturnListController {
    @Autowired
    private ReturnListService returnListService;
    @Autowired
    private ReturnListGoodsService returnListGoodsService;

    @PostMapping("/save")
    public ServiceVO saveReturnList(@RequestParam(required = false) String returnNumber,
                                    ReturnList returnList,
                                    @RequestParam String returnListGoodsStr) {
        returnListService.save(returnNumber, returnList, returnListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/list")
    public Map<String, Object> listReturnList(@RequestParam(required = false) String returnNumber,
                                              @RequestParam(required = false) Integer supplierId,
                                              @RequestParam(required = false) Integer state,
                                              @RequestParam String sTime,
                                              @RequestParam String eTime) {
        return returnListService.list(returnNumber, supplierId, state, sTime, eTime);
    }

    @PostMapping("/goodsList")
    public Map<String, Object> listReturnGoodsById(@RequestParam Integer returnListId) {
        return returnListGoodsService.listReturnGoodsById(returnListId);
    }

    @PostMapping("/delete")
    public ServiceVO deleteById(@RequestParam Integer returnListId) {
        returnListService.deleteById(returnListId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/count")
    public String returnStatistics(@RequestParam(required = false) Integer goodsTypeId,
                                   @RequestParam(required = false) String codeOrName,
                                   @RequestParam String sTime,
                                   @RequestParam String eTime) {
        return returnListService.returnStatistics(goodsTypeId, codeOrName, sTime, eTime);
    }
}
