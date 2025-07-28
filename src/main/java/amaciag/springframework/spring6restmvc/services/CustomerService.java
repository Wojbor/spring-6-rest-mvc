package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    Optional<CustomerDTO> getCustomerById(UUID id);

    List<CustomerDTO> listCustomers();

    CustomerDTO saveCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer);

    Optional<CustomerDTO> patchCustomer(UUID customerId, CustomerDTO customer);

    void deleteById(UUID customerId);
}
