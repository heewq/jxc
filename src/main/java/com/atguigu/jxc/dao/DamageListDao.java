package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DamageListDao {
    void save(DamageList damageList);

    List<DamageList> list(@Param("sTime") String sTime, @Param("eTime") String eTime);
}
