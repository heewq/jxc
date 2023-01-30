package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowListGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OverflowListGoodsDao {
    void save(OverflowListGoods goods);

    List<OverflowListGoods> listOverflowGoodsById(@Param("overflowListId") Integer overflowListId);
}
