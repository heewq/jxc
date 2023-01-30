package com.atguigu.jxc.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleListGoodsDao {
    Integer getSaleTotalByGoodsId(@Param("goodsId") Integer goodsId);
}
