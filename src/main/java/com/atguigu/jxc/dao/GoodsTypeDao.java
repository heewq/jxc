package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.GoodsType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsTypeDao {
    List<GoodsType> getAllGoodsTypeByPID(@Param("PId") Integer PId);

//    List<GoodsType> getAllGoodsTypes();

    void save(@Param("goodsTypeName") String goodsTypeName,
              @Param("pId") Integer pId,
              @Param("goodsTypeState") Integer goodsTypeState);

    Integer deleteById(@Param("goodsTypeId") Integer goodsTypeId);

    List<GoodsType> getAllGoodsType();

    GoodsType getById(@Param("goodsTypeId") Integer goodsTypeId);

    void update(GoodsType goodsType);
}
