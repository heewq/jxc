package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SupplierDao;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierDao supplierDao;

    @Override
    public Map<String, Object> searchSupplier(Integer page, Integer rows, String supplierName) {
        page = page == 0 ? 1 : page;
        Integer offset = (page - 1) * rows;
        List<Supplier> suppliers = supplierDao.searchSupplier(offset, rows, supplierName);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", suppliers);
        map.put("total", supplierDao.getTotalSuppliers(supplierName));
        return map;
    }

    @Override
    public void save(Supplier supplier) {
        supplierDao.save(supplier);
    }

    @Override
    public void updateById(Supplier supplier) {
        supplierDao.updateById(supplier);
    }

    @Override
    public void deleteById(String ids) {
        String[] idList = ids.split(",");
        supplierDao.deleteById(idList);
    }

    @Override
    public List<Supplier> comboboxListSupplier(String supplierName) {
        return supplierDao.comboboxListSupplier(supplierName);
    }
}
