package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.CustomerReturnListGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerReturnListGoodsDao {
    Integer getCustomerReturnTotalByGoodsId(@Param("goodsId") Integer goodsId);

    void save(CustomerReturnListGoods goods);

    List<CustomerReturnListGoods> listCustomerReturnGoodsById(@Param("customerReturnListId") Integer customerReturnListId);

    void deleteByCustomerReturnListId(@Param("customerReturnListId") Integer customerReturnListId);
}
