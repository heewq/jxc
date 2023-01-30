package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.CustomerReturnList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerReturnListDao {
    void save(CustomerReturnList customerReturnList);

    List<CustomerReturnList> list(@Param("returnNumber") String returnNumber,
                                  @Param("customerId") Integer customerId,
                                  @Param("state") Integer state,
                                  @Param("sTime") String sTime,
                                  @Param("eTime") String eTime);

    void deleteById(@Param("customerReturnListId") Integer customerReturnListId);
}
