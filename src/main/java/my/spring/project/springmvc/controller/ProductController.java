package my.spring.project.springmvc.controller;

import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("market")
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

    @GetMapping("/products/{category}")
    public String getProductsByCategory(@PathVariable final String category, final Model model) {
        final List<Product> products = service.getProductsByCategory(category);

        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping("/products/filter/{params}")
    @SuppressWarnings("MVCPathVariableInspection")
    public String getProductsByFilter(
            @MatrixVariable(pathVar = "params") final Map<String, List<String>> filterParams,
            final Model model) {

        final List<Product> products = service.getProductsByFilter(filterParams);
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/update/stock")
    public String updateStock() {
        service.updateAllStock();
        return "redirect:/market/products";
    }
}