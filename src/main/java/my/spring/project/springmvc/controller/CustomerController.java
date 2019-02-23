package my.spring.project.springmvc.controller;

import my.spring.project.springmvc.domain.Customer;
import my.spring.project.springmvc.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static my.spring.project.springmvc.controller.CustomerController.TEXT_HTML_UTF_8_VALUE;

@Controller
@RequestMapping(produces = TEXT_HTML_UTF_8_VALUE)
public class CustomerController {
    static final String TEXT_HTML_UTF_8_VALUE = "text/html; charset=UTF-8";

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

    @GetMapping("/customers/add")
    public String getAddNewCustomerForm(@ModelAttribute("newCustomer") final Customer customer) {
        return "addCustomer";
    }

    @PostMapping("/customers/add")
    public String processAddNewCustomerForm(
            @ModelAttribute("newCustomer") @Valid final Customer customer,
            final BindingResult result) {

        if (result.hasErrors()) {
            return "addCustomer";
        }

        service.addCustomer(customer);

        return "redirect:/customers";
    }
}