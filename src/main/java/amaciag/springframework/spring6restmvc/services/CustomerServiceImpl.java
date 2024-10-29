package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, Customer> customersDataMap;

    public CustomerServiceImpl() {
        this.customersDataMap = new HashMap<>();

        Customer customer1 = Customer
                .builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Arek")
                .createdDate(LocalDateTime.now())
                .lastModifyDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer
                .builder()
                .id(UUID.randomUUID())
                .version(2)
                .customerName("Lila")
                .createdDate(LocalDateTime.now())
                .lastModifyDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer
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
    public Customer getCustomerById(UUID id) {
        return customersDataMap.get(id);
    }

    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customersDataMap.values());
    }
}