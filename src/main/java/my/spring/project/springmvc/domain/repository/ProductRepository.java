package my.spring.project.springmvc.domain.repository;

import my.spring.project.springmvc.domain.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductRepository {
    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByFilter(Map<String, List<String>> filterParams);

    List<Product> filterProducts(String category, String brand, Map<String, BigDecimal> price);

    Product getProductById(String productId);

    void addProduct(Product product);

    void updateStock(String productId, long noOfUnits);
}