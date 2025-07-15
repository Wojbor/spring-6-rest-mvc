package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.entities.Customer;
import amaciag.springframework.spring6restmvc.mappers.CustomerMapper;
import amaciag.springframework.spring6restmvc.model.CustomerDTO;
import amaciag.springframework.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDTO> customersDataMap;

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        Customer foundCustomer = customerRepository.findById(id).get();
        return Optional.of(customerMapper.customerToCustomerDto(foundCustomer));
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> foundCustomers = customerRepository.findAll();
        return foundCustomers.stream().map(customerMapper::customerToCustomerDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {

        Customer savedCustomer = customerMapper.customerDtoToCustomer(customer);

        savedCustomer.setCreatedDate(LocalDateTime.now());
        savedCustomer.setLastModifyDate(LocalDateTime.now());

        customerRepository.save(savedCustomer);

        return customerMapper.customerToCustomerDto(savedCustomer);
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {
        Customer customerToUpdate = customerRepository.findById(customerId).get();
        customerToUpdate.setCustomerName(customer.getCustomerName());
        customerToUpdate.setLastModifyDate(LocalDateTime.now());
        customerRepository.save(customerToUpdate);
    }

    @Override
    public void deleteById(UUID customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public void patchCustomer(UUID customerId, CustomerDTO customer) {
        Customer customerToUpdate = customerRepository.findById(customerId).get();

        if (StringUtils.hasText(customer.getCustomerName())) {
            customerToUpdate.setCustomerName(customer.getCustomerName());
        }

        if (customer.getVersion() != null) {
            customerToUpdate.setVersion(customer.getVersion());
        }

        customerRepository.save(customerToUpdate);
    }
}
