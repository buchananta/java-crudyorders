package com.lambda.orders.services;

import com.lambda.orders.models.Customer;
import com.lambda.orders.models.Order;
import com.lambda.orders.models.Payment;
import com.lambda.orders.repositories.CustomerRepository;
import com.lambda.orders.repositories.OrderRepository;
import com.lambda.orders.repositories.PaymentRepository;
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
    @Autowired
    OrderRepository ordrepos;
    @Autowired
    PaymentRepository payrepos;

    @Transactional
    @Override
    public Customer save(Customer customer)
    {
        return custrepos.save(customer);
    }

    @Transactional
    @Override
    public Customer update(Customer customer, long id)
    {
        Customer currentCustomer = custrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer " + id +
                    " Not Found"));
        if (customer.getCustname() != null)
        {
            currentCustomer.setCustname(customer.getCustname());
        }
        if (customer.getCustcity() != null)
        {
            currentCustomer.setCustcity(customer.getCustcity());
        }
        if (customer.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if (customer.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }
        if (customer.getGrade() != null)
        {
            currentCustomer.setGrade(customer.getGrade());
        }
        if (customer.getOpeningamt() != null)
        {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }
        if (customer.getReceiveamt() != null)
        {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }
        if (customer.getPaymentamt() != null)
        {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }
        if (customer.getOutstandingamt() != null)
        {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }
        if (customer.getPhone() != null)
        {
            currentCustomer.setPhone(customer.getPhone());
        }
        if (customer.getOrders().size() > 0 )
        {
            currentCustomer.getOrders().clear();
            for (Order o : customer.getOrders())
            {
                Order newOrder = new Order(o.getOrdamount(),
                        o.getAdvanceamount(),
                        currentCustomer,
                        o.getOrderdescription());
                currentCustomer.getOrders().add(newOrder);
            }
        }
        return custrepos.save(currentCustomer);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (custrepos.findById(id).isPresent())
        {
            custrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Customer " + id + "Not Found");
        }
    }

    // I need to actually implement save here yet
    // I don't think I can add orders :(
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
