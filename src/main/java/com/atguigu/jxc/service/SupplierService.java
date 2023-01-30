package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.Supplier;

import java.util.List;
import java.util.Map;

public interface SupplierService {
    Map<String, Object> searchSupplier(Integer page, Integer rows, String supplierName);

    void save(Supplier supplier);

    void updateById(Supplier supplier);

    void deleteById(String ids);

    List<Supplier> comboboxListSupplier(String supplierName);
}
