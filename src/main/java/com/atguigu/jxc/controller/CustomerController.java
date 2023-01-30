package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/list")
    public Map<String, Object> listCustomer(Integer page, Integer rows, String customerName) {
        return customerService.listCustomer(page, rows, customerName);
    }

    @PostMapping("/save")
    public ServiceVO InsertOrUpdate(@RequestParam(required = false) Integer customerId,
                                    Customer customer) {
        if (customerId == null) {
            customerService.insert(customer);
        } else {
            customer.setCustomerId(customerId);
            customerService.updateById(customer);
        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping("/delete")
    public ServiceVO delete(@RequestParam String ids) {
        customerService.deleteById(ids);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }
}
