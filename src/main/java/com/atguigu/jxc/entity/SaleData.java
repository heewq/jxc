package com.atguigu.jxc.entity;

import lombok.Data;
/**
 * 销售实体封装
 */
@Data
public class SaleData {

    private String date;
    private Double saleTotal;
    private Double purchasingTotal;
    private Double profit;
}
