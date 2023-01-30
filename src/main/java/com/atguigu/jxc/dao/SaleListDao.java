package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.SaleList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleListDao {
    void save(SaleList saleList);

    List<SaleList> list(@Param("saleNumber") String saleNumber,
                        @Param("customerId") Integer customerId,
                        @Param("state") Integer state,
                        @Param("sTime") String sTime,
                        @Param("eTime") String eTime);

    void deleteById(@Param("saleListId") Integer saleListId);
}
