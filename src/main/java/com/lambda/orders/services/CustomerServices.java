package com.lambda.orders.services;

import com.lambda.orders.models.Customer;

import java.util.List;

public interface CustomerServices
{
    Customer save(Customer customer);

    List<Customer> findAllCustomers();

    List<Customer> findCustomerBySubName(String subName);

    Customer findByCustomerId(long id);
}
