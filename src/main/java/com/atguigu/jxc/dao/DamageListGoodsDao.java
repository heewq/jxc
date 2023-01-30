package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageListGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DamageListGoodsDao {
    void save(DamageListGoods goods);

    List<DamageListGoods> listDamageGoodsById(@Param("damageListId") Integer damageListId);
}
