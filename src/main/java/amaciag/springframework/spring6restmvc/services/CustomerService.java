package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer getCustomerById(UUID id);
    List<Customer> listCustomers();
}