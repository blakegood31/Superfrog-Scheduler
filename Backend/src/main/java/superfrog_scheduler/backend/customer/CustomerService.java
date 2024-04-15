package superfrog_scheduler.backend.customer;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import superfrog_scheduler.backend.customer.Customer;
import superfrog_scheduler.backend.utils.IdWorker;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final IdWorker idWorker;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
        this.idWorker = new IdWorker();
    }

    public List<Customer> findAll(){
        return this.customerRepository.findAll();
    }
}
