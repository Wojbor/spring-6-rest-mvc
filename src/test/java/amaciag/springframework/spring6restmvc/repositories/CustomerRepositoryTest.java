package amaciag.springframework.spring6restmvc.repositories;

import amaciag.springframework.spring6restmvc.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void saveCustomer() {
        Customer customer = customerRepository.save(Customer.builder().customerName("Wojbor").build());

        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isNotNull();
    }
}