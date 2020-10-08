package com.lambda.orders.controllers;

import com.lambda.orders.models.Customer;
import com.lambda.orders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    @Autowired
    CustomerServices customerService;

    @GetMapping(value="/orders", produces = {"application/json"})
    public ResponseEntity<?> findAllCustomerOrders()
    {
        List<Customer> rtnList = customerService.findAllCustomers();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @GetMapping(value="customer/{id}", produces = {"application/json"})
    public ResponseEntity<?> findById(@PathVariable long id)
    {
        Customer rtnCustomer = customerService.findByCustomerId(id);
        return new ResponseEntity<>(rtnCustomer, HttpStatus.OK);
    }

    @GetMapping(value="namelike/{subname}", produces = {"application/json"})
    public ResponseEntity<?> findCustomersBySubName(@PathVariable String subname)
    {
        List<Customer> rtnList = customerService.findCustomerBySubName(subname);

        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }
}
