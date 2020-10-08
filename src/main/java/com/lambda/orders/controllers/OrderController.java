package com.lambda.orders.controllers;

import com.lambda.orders.models.Customer;
import com.lambda.orders.models.Order;
import com.lambda.orders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController
{
    @Autowired
    OrderServices ordrepos;

    @GetMapping(value = "order/{id}", produces = {"application/json"})
    public ResponseEntity<?> findOrderById(@PathVariable long id)
    {
        Order rtnOrder = ordrepos.findByOrderId(id);
        System.out.println(rtnOrder.getOrdnum());
        return new ResponseEntity<>(rtnOrder, HttpStatus.OK);
    }

    @GetMapping(value = "orders", produces = {"application/json"})
    public ResponseEntity<?> findAllOrders()
    {
        List<Order> rtnList = ordrepos.findAllOrders();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }
}
