package my.spring.project.springmvc.controller;

import my.spring.project.springmvc.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Controller
public class ProductController {

    @GetMapping("/products")
    public String list(final Model model) {
        final Product iPhone = new Product("P1234", "iPhone XS", new BigDecimal(1500));
        iPhone.setDescription("Apple iPhone XS smartphone with 5.8-inches 1125 x 2436 display and 12MP main camera");
        iPhone.setCategory("Smartphone");
        iPhone.setManufacturer("Apple");
        iPhone.setUnitsInStock(1000);

        model.addAttribute("product", iPhone);
        return "products";
    }
}
