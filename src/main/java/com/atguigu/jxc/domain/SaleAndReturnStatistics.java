package com.atguigu.jxc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaleAndReturnStatistics extends Statistics {
    private String customerName;
}
