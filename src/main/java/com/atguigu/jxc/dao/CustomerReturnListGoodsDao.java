package com.atguigu.jxc.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReturnListGoodsDao {
    Integer getCustomerReturnTotalByGoodsId(@Param("goodsId") Integer goodsId);
}
