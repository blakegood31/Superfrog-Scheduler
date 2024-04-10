package superfrog_scheduler.backend.customer;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import superfrog_scheduler.backend.customer.converter.CustomerDtoToCustomerConverter;
import superfrog_scheduler.backend.customer.converter.CustomerToCustomerDtoConverter;
import superfrog_scheduler.backend.student.Student;
import superfrog_scheduler.backend.system.Result;
import superfrog_scheduler.backend.system.StatusCode;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerToCustomerDtoConverter customerToCustomerDtoConverter;
    private final CustomerDtoToCustomerConverter customerDtoToCustomerConverter;

    public CustomerController(CustomerService customerService, CustomerToCustomerDtoConverter customerToCustomerDtoConverter, CustomerDtoToCustomerConverter customerDtoToCustomerConverter){
        this.customerService = customerService;
        this.customerToCustomerDtoConverter = customerToCustomerDtoConverter;
        this.customerDtoToCustomerConverter = customerDtoToCustomerConverter;
    }

    @GetMapping("/customers")
    public Result findAllCustomers(){
        List<Customer> customerDtos = this.customerService.findAll();
        customerDtos.stream()
                .map(this.customerToCustomerDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find all success", customerDtos);
    }
}
