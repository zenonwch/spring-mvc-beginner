package my.spring.project.springmvc.validator;

import my.spring.project.springmvc.domain.Product;
import org.apache.commons.io.FileUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductImageValidator implements Validator {

    private final long allowedSize;

    public ProductImageValidator(final long allowedSize) {
        this.allowedSize = allowedSize;
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final Product product = (Product) target;

        final String displayAllowedSize = FileUtils.byteCountToDisplaySize(allowedSize);

        if (product.getProductImage().getSize() > allowedSize) {
            errors.rejectValue("productImage", "validator.productImage.size.message", new Object[]{displayAllowedSize}, null);
        }
    }
}