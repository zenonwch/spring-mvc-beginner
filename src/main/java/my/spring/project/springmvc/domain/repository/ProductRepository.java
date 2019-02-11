package my.spring.project.springmvc.domain.repository;

import my.spring.project.springmvc.domain.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();

    void updateStock(String productId, long noOfUnits);
}