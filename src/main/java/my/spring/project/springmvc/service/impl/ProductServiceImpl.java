package my.spring.project.springmvc.service.impl;

import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.domain.repository.ProductRepository;
import my.spring.project.springmvc.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(final ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateAllStock() {
        final List<Product> allProducts = repository.getAllProducts();

        for (final Product product : allProducts) {
            final long unitsInStock = product.getUnitsInStock();

            if (unitsInStock < 500) {
                repository.updateStock(product.getProductId(), unitsInStock + 100);
            }
        }
    }
}