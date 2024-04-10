package superfrog_scheduler.backend.customer.converter;

import superfrog_scheduler.backend.customer.Customer;
import superfrog_scheduler.backend.customer.dto.CustomerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToCustomerConverter implements Converter<CustomerDto, Customer> {

    @Override
    public Customer convert(CustomerDto source){
        Customer customer = new Customer();
        customer.setId(source.id());
        customer.setName(source.name());
        customer.setEmail(source.email());
        customer.setPhone(source.phone());
        return customer;
    }
}
