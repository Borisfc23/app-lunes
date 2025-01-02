package com.synopsis.capacitacion.customer.service;
import java.util.List;

import com.synopsis.capacitacion.customer.entities.Customer;
import com.synopsis.capacitacion.customer.entities.CustomerProduct;
public interface ICustomerService {
     //GET
    Object findById(long id);

    List<Customer> findAll();

    Object findByCode(String code);

    Object findByName(String name);

    Object findByAccountNumber(String accountNumber);

    //POST
    Object createCustomer (Customer customer);

    //UPDATE
    Object updateCustomer (long id, Customer customer);

    Object updateProducts (long id, List<CustomerProduct> products);

    //DELETE
    Object deleteById (long id);

    Object deleteAll ();
}
