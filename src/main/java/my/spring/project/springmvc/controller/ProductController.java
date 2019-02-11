package my.spring.project.springmvc.controller;

import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService service;

    public ProductController(final ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public String list(final Model model) {
        final List<Product> products = service.getAllProducts();

        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/update/stock")
    public String updateStock() {
        service.updateAllStock();
        return "redirect:/products";
    }
}