package my.spring.project.springmvc.controller;

import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.domain.repository.ProductRepository;
import my.spring.project.springmvc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService service;
    private final ProductRepository repository;

    public ProductController(final ProductService service, final ProductRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/products")
    public String list(final Model model) {
        final List<Product> products = repository.getAllProducts();

        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/update/stock")
    public String updateStock() {
        service.updateAllStock();
        return "redirect:/products";
    }
}