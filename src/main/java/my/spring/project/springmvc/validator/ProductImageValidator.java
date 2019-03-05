package my.spring.project.springmvc.validator;

import my.spring.project.springmvc.domain.Product;
import org.apache.commons.io.FileUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

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

        final MultipartFile productImage = product.getProductImage();
        if (productImage != null && productImage.getSize() > allowedSize) {
            errors.rejectValue("productImage", "validator.productImage.size.message", new Object[]{displayAllowedSize}, null);
        }
    }
}