package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.ReturnList;
import com.atguigu.jxc.service.ReturnListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/returnListGoods")
public class ReturnListController {
    @Autowired
    private ReturnListService returnListService;

    @PostMapping("/save")
    public ServiceVO saveReturnList(@RequestParam(required = false) String returnNumber,
                                    ReturnList returnList,
                                    @RequestParam String returnListGoodsStr) {
        returnListService.save(returnNumber, returnList, returnListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }
}
