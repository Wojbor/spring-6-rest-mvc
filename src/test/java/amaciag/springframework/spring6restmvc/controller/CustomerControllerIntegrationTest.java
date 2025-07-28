package amaciag.springframework.spring6restmvc.controller;

import amaciag.springframework.spring6restmvc.entities.Customer;
import amaciag.springframework.spring6restmvc.mappers.CustomerMapper;
import amaciag.springframework.spring6restmvc.model.CustomerDTO;
import amaciag.springframework.spring6restmvc.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
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

    @Autowired
    CustomerMapper customerMapper;

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

    @Test
    @Transactional
    @Rollback
    void testSaveNewBeer() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerName("ArekTest")
                .createdDate(LocalDateTime.now())
                .lastModifyDate(LocalDateTime.now())
                .build();

        ResponseEntity responseEntity = customerController.createCustomer(customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(201));

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedCustomerId = UUID.fromString(locationUUID[4]);

        Customer customer = customerRepository.findById(savedCustomerId).get();
        assertThat(customer).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    void testUpdateCustomerById() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setCustomerName("ArekTest");

        ResponseEntity responseEntity = customerController.updateCustomerById(customer.getId(), customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(customerDTO.getCustomerName());
    }

    @Test
    void testUpdateCustomerByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.updateCustomerById(UUID.randomUUID(), CustomerDTO.builder().build());
        });
    }

    @Test
    void testPatchCustomerById() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setCustomerName("ArekTest");

        ResponseEntity responseEntity = customerController.patchCustomerById(customer.getId(), customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(customerDTO.getCustomerName());
    }

    @Test
    void testPatchCustomerByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.patchCustomerById(UUID.randomUUID(), CustomerDTO.builder().build());
        });
    }
}