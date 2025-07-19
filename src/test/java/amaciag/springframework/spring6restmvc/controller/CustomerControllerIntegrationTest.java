package amaciag.springframework.spring6restmvc.controller;

import amaciag.springframework.spring6restmvc.entities.Customer;
import amaciag.springframework.spring6restmvc.model.CustomerDTO;
import amaciag.springframework.spring6restmvc.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIntegrationTest {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void getCustomerByIdExceptionTest() {
        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomerById(UUID.randomUUID());
        });
    }

    @Test
    void getCustomerByIdTest() {
        Customer customer = customerRepository.findAll().get(0);

        CustomerDTO foundCustomer = customerController.getCustomerById(customer.getId());

        assertThat(foundCustomer).isNotNull();
    }

    @Test
    void getAllCustomerTest() {
        List<CustomerDTO> customerDTOs = customerController.getAllCustomers();

        assertThat(customerDTOs.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    @Rollback
    void getEmptyAllCustomerTest() {
        customerRepository.deleteAll();

        List<CustomerDTO> customerDTOs = customerController.getAllCustomers();

        assertThat(customerDTOs.size()).isEqualTo(0);
    }
}