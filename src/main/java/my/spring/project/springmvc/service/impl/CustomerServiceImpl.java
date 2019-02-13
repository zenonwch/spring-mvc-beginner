package my.spring.project.springmvc.service.impl;

import my.spring.project.springmvc.domain.Customer;
import my.spring.project.springmvc.domain.repository.CustomerRepository;
import my.spring.project.springmvc.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(final CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.getAllCustomers();
    }

    @Override
    public void addCustomer(final Customer customer) {
        repository.addCustomer(customer);
    }
}