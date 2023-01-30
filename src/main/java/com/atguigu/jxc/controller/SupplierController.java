package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/list")
    public Map<String, Object> listSupplier(@RequestParam Integer page,
                                            @RequestParam Integer rows,
                                            @RequestParam(required = false) String supplierName) {
        return supplierService.searchSupplier(page, rows, supplierName);
    }

    @PostMapping("/save")
    public ServiceVO saveOrUpdate(@RequestParam(required = false) Integer supplierId,
                                  Supplier supplier) {
        if (supplierId == null) {
            supplierService.save(supplier);
        } else {
            supplier.setSupplierId(supplierId);
            supplierService.updateById(supplier);
        }
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/delete")
    public ServiceVO delete(@RequestParam String ids) {
        Assert.notNull(ids, "Invalid Parameter");
        supplierService.deleteById(ids);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/getComboboxList")
    public List<Supplier> comboboxListSupplier(@RequestParam(value = "q", required = false) String supplierName) {
        return supplierService.comboboxListSupplier(supplierName);
    }
}
