package my.spring.project.springmvc.domain.repository;

import my.spring.project.springmvc.domain.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getAllCustomers();
}