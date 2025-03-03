package amaciag.springframework.spring6restmvc.controller;

import amaciag.springframework.spring6restmvc.model.Customer;
import amaciag.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PatchMapping("{customerId}")
    public ResponseEntity patchCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {

        customerService.patchCustomer(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    private ResponseEntity deleteCustomer(@PathVariable("customerId") UUID customerId) {

        customerService.deleteById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{customerId}")
    public ResponseEntity updateCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {

        customerService.updateCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    ResponseEntity createCustomer(@RequestBody Customer customer) {

        Customer savedCustomer = customerService.saveCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/customer" + savedCustomer.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);

    }

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
