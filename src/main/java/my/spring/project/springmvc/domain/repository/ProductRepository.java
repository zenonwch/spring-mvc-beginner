package my.spring.project.springmvc.domain.repository;

import my.spring.project.springmvc.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository {
    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByFilter(Map<String, List<String>> filterParams);

    Product getProductById(String productId);

    void updateStock(String productId, long noOfUnits);
}