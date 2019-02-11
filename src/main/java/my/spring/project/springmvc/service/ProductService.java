package my.spring.project.springmvc.service;

import my.spring.project.springmvc.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    void updateAllStock();
}