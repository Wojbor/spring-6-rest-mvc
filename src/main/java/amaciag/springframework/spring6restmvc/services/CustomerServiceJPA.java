package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.mappers.CustomerMapper;
import amaciag.springframework.spring6restmvc.model.CustomerDTO;
import amaciag.springframework.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMapper.customerToCustomerDto(customerRepository.findById(id).orElse(null)));
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {
        return customerMapper.customerToCustomerDto(
                customerRepository.save(customerMapper.customerDtoToCustomer(customer)));
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> customerDTOAtomicReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(foundcustomer -> {
            foundcustomer.setCustomerName(customer.getCustomerName());
            customerRepository.save(foundcustomer);
            customerDTOAtomicReference.set(Optional.of(customerMapper.customerToCustomerDto(foundcustomer)));
        }, () -> {
            customerDTOAtomicReference.set(Optional.empty());
        });

        return customerDTOAtomicReference.get();
    }

    @Override
    public void deleteById(UUID customerId) {

    }

    @Override
    public Optional<CustomerDTO> patchCustomer(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> customerDTOAtomicReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(foundcustomer -> {
            if (StringUtils.hasText(customer.getCustomerName()))
                foundcustomer.setCustomerName(customer.getCustomerName());
            customerRepository.save(foundcustomer);
            customerDTOAtomicReference.set(Optional.of(customerMapper.customerToCustomerDto(foundcustomer)));
        }, () -> {
            customerDTOAtomicReference.set(Optional.empty());
        });

        return customerDTOAtomicReference.get();
    }
}
