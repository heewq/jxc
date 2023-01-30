package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.ReturnList;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnListDao {
    void save(ReturnList returnList);
}
