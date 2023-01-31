package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.SaleData;
import com.atguigu.jxc.entity.SaleListGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleListGoodsDao {
    Integer getSaleTotalByGoodsId(@Param("goodsId") Integer goodsId);

    void save(SaleListGoods goods);

    List<SaleListGoods> listSaleGoodsById(@Param("saleListId") Integer saleListId);

    void deleteBySaleListId(@Param("saleListId") Integer saleListId);

    List<SaleData> getSaleDataByDay(@Param("sTime") String sTime, @Param("eTime") String eTime);

    List<SaleData> getSaleDataByMonth(@Param("sTime") String sTime, @Param("eTime") String eTime);
}
