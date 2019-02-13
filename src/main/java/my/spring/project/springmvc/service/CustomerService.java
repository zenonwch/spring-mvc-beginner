package my.spring.project.springmvc.service;

import my.spring.project.springmvc.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    void addCustomer(Customer customer);
}