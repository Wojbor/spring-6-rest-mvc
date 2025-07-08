package amaciag.springframework.spring6restmvc.mappers;

import amaciag.springframework.spring6restmvc.entities.Customer;
import amaciag.springframework.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDto(Customer customer);
}
