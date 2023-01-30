package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.PurchaseList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseListDao {
    void save(PurchaseList purchaseList);

    List<PurchaseList> list(@Param("purchaseNumber") String purchaseNumber,
                            @Param("supplierId") Integer supplierId,
                            @Param("state") Integer state,
                            @Param("sTime") String sTime,
                            @Param("eTime") String eTime);

    void deleteById(@Param("purchaseListId") Integer purchaseListId);
}
