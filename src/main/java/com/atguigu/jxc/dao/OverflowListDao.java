package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OverflowListDao {
    void save(OverflowList overflowList);

    List<OverflowList> list(@Param("sTime") String sTime, @Param("eTime") String eTime);
}
