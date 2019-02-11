package my.spring.project.springmvc.controller;

import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.domain.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    private final ProductRepository repository;

    public ProductController(final ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/products")
    public String list(final Model model) {
        final List<Product> products = repository.getAllProducts();

        model.addAttribute("products", products);
        return "products";
    }
}