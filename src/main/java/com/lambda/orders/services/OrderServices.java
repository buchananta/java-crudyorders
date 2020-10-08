package com.lambda.orders.services;

import com.lambda.orders.models.Order;

import java.util.List;

public interface OrderServices
{
    Order save(Order order);
    Order findByOrderId(long id);
    List<Order> findAllOrders();
}
