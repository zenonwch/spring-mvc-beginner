package my.spring.project.springmvc.validator;

import my.spring.project.springmvc.domain.Category;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

public class CategoryValidator implements ConstraintValidator<ValidCategory, String> {
    @Override
    public void initialize(final ValidCategory constraintAnnotation) {
        // intentionally left blank;
        // this is the place to initialize the constraint annotation
        // for any sensible default values.
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return value == null || value.isEmpty() || Arrays.stream(Category.values())
                .anyMatch(c -> {
                    final String categoryName = c.name();
                    return Objects.equals(categoryName, value);
                });
    }
}