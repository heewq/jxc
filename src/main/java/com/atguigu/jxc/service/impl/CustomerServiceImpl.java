package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.CustomerDao;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public Map<String, Object> listCustomer(Integer page, Integer rows, String customerName) {
        page = page == 0 ? 1 : page;
        Integer offset = (page - 1) * rows;
        List<Customer> customers = customerDao.listCustomer(offset, rows, customerName);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", customers);

        map.put("total", customerDao.getTotalCustomer(customerName));
        return map;
    }

    @Override
    public void insert(Customer customer) {
        customerDao.insert(customer);
    }

    @Override
    public void updateById(Customer customer) {
        customerDao.updateById(customer);
    }

    @Override
    public void deleteById(String ids) {
        String[] idList = ids.split(",");
        customerDao.deleteById(idList);
    }

    @Override
    public List<Customer> comboboxListCustomer(String customerName) {
        return customerDao.comboboxListCustomer(customerName);
    }
}
