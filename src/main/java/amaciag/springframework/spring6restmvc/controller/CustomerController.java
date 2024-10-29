package amaciag.springframework.spring6restmvc.controller;

import amaciag.springframework.spring6restmvc.model.Customer;
import amaciag.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping("{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") UUID id) {
        log.debug("Get customer by id: " + id);
        return customerService.getCustomerById(id);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        log.debug("Get all customers");
        return customerService.listCustomers();
    }
}
