package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Unit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitDao {
    List<Unit> listUnit();
}
