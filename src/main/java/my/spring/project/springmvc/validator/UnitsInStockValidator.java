package my.spring.project.springmvc.validator;

import my.spring.project.springmvc.domain.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

public class UnitsInStockValidator implements Validator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final Product product = (Product) target;

        if (product.getUnitPrice() != null &&
                BigDecimal.valueOf(100).compareTo(product.getUnitPrice()) <= 0 &&
                product.getUnitsInStock() > 99) {

            final int maxPrice = 1000;
            final int maxUnitsInStock = 99;
            errors.rejectValue("unitsInStock", "validator.unitsInStock.message", new Object[]{maxUnitsInStock, maxPrice}, null);
        }
    }
}