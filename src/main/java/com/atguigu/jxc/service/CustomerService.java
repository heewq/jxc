package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    Map<String, Object> listCustomer(Integer page, Integer rows, String customerName);

    void insert(Customer customer);

    void updateById(Customer customer);

    void deleteById(String ids);

    List<Customer> comboboxListCustomer(String customerName);
}
