package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/listInventory")
    public Map<String, Object> listInventory(@RequestParam Integer page,
                                             @RequestParam Integer rows,
                                             @RequestParam(required = false) String codeOrName,
                                             @RequestParam(required = false) Integer goodsTypeId) {
        return goodsService.stockSearch(page, rows, codeOrName, goodsTypeId);
    }

    @PostMapping("/list")
    public Map<String, Object> listGoods(@RequestParam Integer page,
                                         @RequestParam Integer rows,
                                         @RequestParam(required = false) String goodsName,
                                         @RequestParam(required = false) Integer goodsTypeId) {
        return goodsService.listGoods(page, rows, goodsName, goodsTypeId);
    }

    @RequestMapping("/getCode")
    public ServiceVO getCode() {
        String unitCode = goodsService.getCode();
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, unitCode);
    }

    @PostMapping("/save")
    public ServiceVO insertOrUpdate(@RequestParam(required = false) Integer goodsId,
                                    Goods goods) {
        if (goodsId == null) {
            goodsService.insert(goods);
        } else {
            goods.setGoodsId(goodsId);
            goodsService.update(goods);
        }
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/delete")
    public ServiceVO deleteById(@RequestParam Integer goodsId) {
        return goodsService.delete(goodsId);
    }

    @PostMapping("/getNoInventoryQuantity")
    public Map<String, Object> listNoInventoryQuantity(@RequestParam Integer page,
                                                       @RequestParam Integer rows,
                                                       @RequestParam(required = false) String nameOrCode) {
        return goodsService.listNoInventoryQuantity(page, rows, nameOrCode);
    }

    @PostMapping("/getHasInventoryQuantity")
    public Map<String, Object> listHasInventoryQuantity(@RequestParam Integer page,
                                                        @RequestParam Integer rows,
                                                        @RequestParam(required = false) String nameOrCode) {
        return goodsService.listHasInventoryQuantity(page, rows, nameOrCode);
    }

    @PostMapping("/saveStock")
    public ServiceVO updateStock(@RequestParam Integer goodsId,
                                 @RequestParam(required = false) Integer inventoryQuantity,
                                 @RequestParam(required = false) Double purchasingPrice) {

        goodsService.updateById(goodsId, inventoryQuantity, purchasingPrice);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/deleteStock")
    public ServiceVO deleteStock(@RequestParam Integer goodsId) {
        return goodsService.deleteStock(goodsId);
    }

    @PostMapping("/listAlarm")
    public Map<String, Object> listAlarm() {
        return goodsService.listAlarm();
    }
}
