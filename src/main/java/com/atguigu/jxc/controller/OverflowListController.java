package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.OverflowListGoodsService;
import com.atguigu.jxc.service.OverflowListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/overflowListGoods")
public class OverflowListController {
    @Autowired
    private OverflowListService overflowListService;
    @Autowired
    private OverflowListGoodsService overflowListGoodsService;

    @PostMapping("/save")
    public ServiceVO saveOverflowList(@RequestParam(required = false) String overflowNumber,
                                      OverflowList overflowList,
                                      @RequestParam String overflowListGoodsStr,
                                      HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        overflowList.setUserId(user.getUserId());
        overflowListService.save(overflowNumber, overflowList, overflowListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/list")
    public Map<String, Object> listOverflowList(@RequestParam String sTime,
                                                @RequestParam String eTime) {
        return overflowListService.list(sTime, eTime);
    }

    @PostMapping("/goodsList")
    public Map<String, Object> listOverflowGoodsById(Integer overflowListId) {
        return overflowListGoodsService.listOverflowGoodsById(overflowListId);
    }
}
