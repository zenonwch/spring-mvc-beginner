package my.spring.project.springmvc.validator;

import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.exception.ProductNotFoundException;
import my.spring.project.springmvc.service.ProductService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIdValidator implements ConstraintValidator<ProductId, String> {

    private final ProductService productService;

    public ProductIdValidator(final ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void initialize(final ProductId constraintAnnotation) {
        // intentionally left blank;
        // this is the place to initialize the constraint annotation
        // for any sensible default values.
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        try {
            final Product product = productService.getProductById(value);

            if (product != null) {
                return false;
            }
        } catch (final ProductNotFoundException ex) {
            return true;
        }

        return true;
    }
}