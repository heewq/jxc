package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.DamageListGoodsService;
import com.atguigu.jxc.service.DamageListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/damageListGoods")
public class DamageListController {
    @Autowired
    private DamageListService damageListService;
    @Autowired
    private DamageListGoodsService damageListGoodsService;

    @PostMapping("/save")
    public ServiceVO saveDamageList(@RequestParam(required = false) String damageNumber,
                                    DamageList damageList,
                                    @RequestParam String damageListGoodsStr,
                                    HttpSession session) throws IOException {
        User user = (User) session.getAttribute("currentUser");
        damageList.setUserId(user.getUserId());
        damageListService.save(damageNumber, damageList, damageListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/list")
    public Map<String, Object> listDamageList(@RequestParam String sTime,
                                               @RequestParam String eTime) {
        return damageListService.list(sTime, eTime);
    }

    @PostMapping("/goodsList")
    public Map<String, Object> listDamageGoodsById(@RequestParam Integer damageListId) {
        return damageListGoodsService.listDamageGoodsById(damageListId);
    }
}
