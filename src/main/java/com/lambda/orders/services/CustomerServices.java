package com.lambda.orders.services;

import com.lambda.orders.models.Customer;

import java.util.List;

public interface CustomerServices
{
    Customer save(Customer customer);

    Customer update(Customer customer, long id);

    void delete(long id);

    List<Customer> findAllCustomers();

    List<Customer> findCustomerBySubName(String subName);

    Customer findByCustomerId(long id);
}
