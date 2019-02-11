package my.spring.project.springmvc.controller;

import my.spring.project.springmvc.domain.Customer;
import my.spring.project.springmvc.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService service;

    public CustomerController(final CustomerService service) {
        this.service = service;
    }

    @GetMapping("/customers")
    public String list(final Model model) {
        final List<Customer> customers = service.getAllCustomers();

        model.addAttribute("customers", customers);
        return "customers";
    }
}