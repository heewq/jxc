package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Supplier;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierDao {
    List<Supplier> searchSupplier(@Param("offset") Integer offset, @Param("rows") Integer rows, @Param("supplierName") String supplierName);

    Integer getTotalSuppliers(@Param("supplierName") String supplierName);

    void save(@Param("supplier") Supplier supplier);

    void updateById(@Param("supplier") Supplier supplier);

    void deleteById(@Param("idList") String[] idList);

    List<Supplier> comboboxListSupplier(@Param("supplierName") String supplierName);
}
