package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDTO> customersDataMap;

    public CustomerServiceImpl() {
        this.customersDataMap = new HashMap<>();

        CustomerDTO customer1 = CustomerDTO
                .builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Arek")
                .createdDate(LocalDateTime.now())
                .lastModifyDate(LocalDateTime.now())
                .build();

        CustomerDTO customer2 = CustomerDTO
                .builder()
                .id(UUID.randomUUID())
                .version(2)
                .customerName("Lila")
                .createdDate(LocalDateTime.now())
                .lastModifyDate(LocalDateTime.now())
                .build();

        CustomerDTO customer3 = CustomerDTO
                .builder()
                .id(UUID.randomUUID())
                .version(3)
                .customerName("Marek")
                .createdDate(LocalDateTime.now())
                .lastModifyDate(LocalDateTime.now())
                .build();

        customersDataMap.put(customer1.getId(), customer1);
        customersDataMap.put(customer2.getId(), customer2);
        customersDataMap.put(customer3.getId(), customer3);

    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.of(customersDataMap.get(id));
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        return new ArrayList<>(customersDataMap.values());
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {

        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(customer.getVersion())
                .customerName(customer.getCustomerName())
                .createdDate(LocalDateTime.now())
                .lastModifyDate(LocalDateTime.now())
                .build();

        customersDataMap.put(customer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {
        CustomerDTO customerToUpdate = customersDataMap.get(customerId);
        customerToUpdate.setCustomerName(customer.getCustomerName());
        customerToUpdate.setLastModifyDate(LocalDateTime.now());
        customerToUpdate.setVersion(customer.getVersion() + 1);
        customersDataMap.put(customerId, customerToUpdate);
    }

    @Override
    public void deleteById(UUID customerId) {
        customersDataMap.remove(customerId);
    }

    @Override
    public void patchCustomer(UUID customerId, CustomerDTO customer) {
        CustomerDTO customerToUpdate = customersDataMap.get(customerId);

        if (StringUtils.hasText(customer.getCustomerName())) {
            customerToUpdate.setCustomerName(customer.getCustomerName());
        }

        if (customer.getVersion() != null) {
            customerToUpdate.setVersion(customer.getVersion());
        }
    }
}
