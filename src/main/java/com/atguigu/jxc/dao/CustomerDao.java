package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao {
    List<Customer> listCustomer(@Param("offset") Integer offset, @Param("rows") Integer rows, @Param("customerName") String customerName);
    Integer getTotalCustomer(@Param("customerName") String customerName);

    void insert(@Param("customer") Customer customer);

    void updateById(@Param("customer") Customer customer);

    void deleteById(@Param("idList") String[] idList);

    List<Customer> comboboxListCustomer(@Param("customerName") String customerName);
}
