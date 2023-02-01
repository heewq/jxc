package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;

    @RequestMapping("/loadGoodsType")
    public String loadGoodsType() {
        return goodsTypeService.loadGoodsType();
    }

    @PostMapping("/save")
    public ServiceVO save(@RequestParam String goodsTypeName,
                          @RequestParam Integer pId) {
        goodsTypeService.save(goodsTypeName, pId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/delete")
    public ServiceVO delete(@RequestParam Integer goodsTypeId) {
        return goodsTypeService.deleteById(goodsTypeId);
    }
}
