package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.ReturnListGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnListGoodsDao {
    void save(ReturnListGoods goods);

    List<ReturnListGoods> listReturnGoodsById(@Param("returnListId") Integer returnListId);

    void deleteByReturnListId(@Param("returnListId") Integer returnListId);
}
