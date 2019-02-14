package my.spring.project.springmvc.controller;

import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @GetMapping("/products/filter/{params}")
    @SuppressWarnings("MVCPathVariableInspection")
    public String getProductsByFilter(
            @MatrixVariable(pathVar = "params") final Map<String, List<String>> filterParams,
            final Model model
    ) {
        final List<Product> products = service.getProductsByFilter(filterParams);

        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/{category}/{params}")
    @SuppressWarnings("MVCPathVariableInspection")
    public String filterProducts(
            @PathVariable final String category,
            @MatrixVariable(pathVar = "params") final Map<String, BigDecimal> price,
            @RequestParam final String brand, final Model model
    ) {
        final List<Product> products = service.filterProducts(category, brand, price);

        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product")
    public String getProductById(@RequestParam("id") final String productId, final Model model) {
        final Product product = service.getProductById(productId);

        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/products/add")
    public String getAddNewProductForm(final Model model) {
        model.addAttribute("newProduct", new Product());

        return "addProduct";
    }

    @PostMapping("/products/add")
    public String processAddNewProductForm(@ModelAttribute("newProduct") final Product product,
                                           final BindingResult result,
                                           final HttpServletRequest request) {

        final String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: " + String.join(",", suppressedFields));
        }

        final MultipartFile productImage = product.getProductImage();

        if (productImage != null && !productImage.isEmpty()) {
            final String rootDir = request.getSession().getServletContext().getRealPath("/");
            final Path destination = Paths.get(rootDir, "resources", "images", product.getProductId() + ".png");
            uploadFile(productImage, destination);
        }

        service.addProduct(product);

        return "redirect:/market/products";
    }

    @GetMapping("/update/stock")
    public String updateStock() {
        service.updateAllStock();
        return "redirect:/market/products";
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.setAllowedFields("productId", "name", "unitPrice", "description",
                "manufacturer", "category", "unitsInStock", "condition", "productImage");
    }

    private void uploadFile(final MultipartFile productImage, final Path destination) {
        try {
            productImage.transferTo(destination);
        } catch (final IOException e) {
            throw new RuntimeException("Product image saving failed", e);
        }
    }
}