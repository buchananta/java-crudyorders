package com.lambda.orders.controllers;

import com.lambda.orders.models.Customer;
import com.lambda.orders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    @PostMapping(value="/customer", consumes = "application/json")
    public ResponseEntity<?> addNewCustomer( @Valid @RequestBody Customer newCustomer)
    {
        newCustomer.setCustcode(0);
        newCustomer = customerService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{customerid}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    @PutMapping(value="/customer/{id}", consumes = "application/json")
    public ResponseEntity<?> updateFullConsumer(@PathVariable long id,
                                                @RequestBody Customer updateCustomer)
    {
        updateCustomer.setCustcode(id);
        customerService.save(updateCustomer);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping(value="/customer/{id}", consumes = "application/json")
    public ResponseEntity<?> updateConsumer(@PathVariable long id,
                                            @RequestBody Customer updateCustomer)
    {
        customerService.update(updateCustomer, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value="/customer/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id)
    {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
