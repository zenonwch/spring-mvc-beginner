package my.spring.project.springmvc.validator;

import my.spring.project.springmvc.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.function.Consumer;

public class ProductValidator implements Validator {

    private final Set<Validator> springValidators;

    @Autowired
    private javax.validation.Validator beanValidator;

    public ProductValidator(final Set<Validator> springValidators) {
        this.springValidators = springValidators;
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final Set<ConstraintViolation<Object>> constraintViolations = beanValidator.validate(target);

        for (final ConstraintViolation<Object> violation : constraintViolations) {
            final String field = violation.getPropertyPath().toString();
            final String message = violation.getMessage();

            errors.rejectValue(field, "", message);
        }

        final Consumer<Validator> validateTarget = v -> v.validate(target, errors);
        springValidators.forEach(validateTarget);
    }
}