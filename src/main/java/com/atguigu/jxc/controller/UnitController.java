package com.atguigu.jxc.controller;

import com.atguigu.jxc.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UnitController {
    @Autowired
    private UnitService unitService;

    @PostMapping("/unit/list")
    public Map<String, Object> listUnit() {
        return unitService.listUnit();
    }
}
