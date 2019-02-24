package my.spring.project.springmvc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryValidator implements ConstraintValidator<Category, String> {
    static final String CATEGORIES = "Smartphone, Tablet, Laptop";

    @Override
    public void initialize(final Category constraintAnnotation) {
        // intentionally left blank;
        // this is the place to initialize the constraint annotation
        // for any sensible default values.
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return CATEGORIES.contains(value);
    }
}