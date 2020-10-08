package com.lambda.orders.services;

import com.lambda.orders.models.Customer;
import com.lambda.orders.repositories.CustomerRepository;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServicesImpl implements CustomerServices
{
    @Autowired
    CustomerRepository custrepos;

    @Transactional
    @Override
    public Customer save(Customer customer)
    {
        return custrepos.save(customer);
    }

    @Override
    public List<Customer> findAllCustomers()
    {
        List<Customer> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Customer> findCustomerBySubName(String subName)
    {
        return custrepos.findByCustnameContainingIgnoringCase(subName);
    }

    @Override
    public Customer findByCustomerId(long id)
    {
        Customer rtnCustomer = custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer" + id + "Not Found"));
        return rtnCustomer;
    }
}
