package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.ReturnList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnListDao {
    void save(ReturnList returnList);

    List<ReturnList> list(@Param("returnNumber") String returnNumber,
                          @Param("supplierId") Integer supplierId,
                          @Param("state") Integer state,
                          @Param("sTime") String sTime,
                          @Param("eTime") String eTime);

    void deleteById(@Param("returnListId") Integer returnListId);
}
