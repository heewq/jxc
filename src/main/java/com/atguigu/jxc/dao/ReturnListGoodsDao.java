package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.ReturnListGoods;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnListGoodsDao {
    void save(ReturnListGoods goods);
}
