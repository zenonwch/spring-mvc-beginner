package my.spring.project.springmvc.service.impl;

import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.domain.repository.ProductRepository;
import my.spring.project.springmvc.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(final ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }

    @Override
    public List<Product> getProductsByCategory(final String category) {
        return repository.getProductsByCategory(category);
    }

    @Override
    public List<Product> getProductsByFilter(final Map<String, List<String>> filterParams) {
        return repository.getProductsByFilter(filterParams);
    }

    @Override
    public List<Product> filterProducts(final String category, final String brand, final Map<String, BigDecimal> price) {
        return repository.filterProducts(category, brand, price);
    }

    @Override
    public Product getProductById(final String productId) {
        return repository.getProductById(productId);
    }

    @Override
    @Transactional
    public void updateAllStock() {
        final Consumer<Product> updateUnitsInStock = product ->
                repository.updateStock(product.getProductId(), product.getUnitsInStock() + 100);

        repository.getAllProducts().stream()
                .filter(product -> product.getUnitsInStock() < 500)
                .forEach(updateUnitsInStock);
    }
}