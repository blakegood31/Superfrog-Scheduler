package superfrog_scheduler.backend.customer.converter;

import org.springframework.stereotype.Component;
import superfrog_scheduler.backend.customer.Customer;
import superfrog_scheduler.backend.customer.dto.CustomerDto;
import org.springframework.core.convert.converter.Converter;

@Component
public class CustomerToCustomerDtoConverter implements Converter<Customer, CustomerDto> {

    @Override
    public CustomerDto convert(Customer source){
        CustomerDto customerDto = new CustomerDto(
                source.getId(),
                source.getName(),
                source.getEmail(),
                source.getPhone()
        );
        return customerDto;
    }

}
