package com.synopsis.capacitacion.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.synopsis.capacitacion.customer.entities.Customer;
import com.synopsis.capacitacion.customer.entities.CustomerProduct;
import com.synopsis.capacitacion.customer.repository.CustomerRepository;
import com.synopsis.capacitacion.customer.service.ICustomerService;
import com.synopsis.capacitacion.customer.webclient.IWebClientCustomer;

public class CustomerServiceImpl implements ICustomerService{
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IWebClientCustomer webClientCustomer;

    @Override
    public Object findById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Customer> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Object findByCode(String code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCode'");
    }

    @Override
    public Object findByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }

    @Override
    public Object findByAccountNumber(String accountNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByAccountNumber'");
    }

    @Override
    public Object createCustomer(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCustomer'");
    }

    @Override
    public Object updateCustomer(long id, Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomer'");
    }

    @Override
    public Object updateProducts(long id, List<CustomerProduct> products) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProducts'");
    }

    @Override
    public Object deleteById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Object deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }
}
