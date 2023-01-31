package com.atguigu.jxc.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GoodsTypeVo {
    private Integer id;
    private String text;
    private String state;
    private String iconCls;
    private Map<String, Integer> attributes;

    private List<GoodsTypeVo> children;
}
