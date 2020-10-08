package com.lambda.orders.services;

import com.lambda.orders.models.Customer;
import com.lambda.orders.models.Order;
import com.lambda.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "orderService")
public class OrderServicesImpl implements OrderServices
{
    @Autowired
    OrderRepository ordrepos;

    @Transactional
    @Override
    public Order save(Order order)
    {
        return ordrepos.save(order);
    }

    @Override
    public Order findByOrderId(long id)
    {
        Order rtnOrder = ordrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order " + id + "Not Found"));
        return rtnOrder;
    }

    @Override
    public List<Order> findAllOrders()
    {
        List<Order> list = new ArrayList<>();
        ordrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
