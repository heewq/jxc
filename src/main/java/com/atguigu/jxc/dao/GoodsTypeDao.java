package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.GoodsType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsTypeDao {
    List<GoodsType> getAllGoodsTypeByPID(@Param("PID") Integer PID);

//    List<GoodsType> getAllGoodsTypes();

    void save(@Param("goodsTypeName") String goodsTypeName, @Param("pId") Integer pId);

    void deleteById(@Param("goodsTypeId") Integer goodsTypeId);

    List<GoodsType> getAllGoodsType();
}
