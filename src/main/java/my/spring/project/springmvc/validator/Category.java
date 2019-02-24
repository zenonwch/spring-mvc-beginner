package my.spring.project.springmvc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static my.spring.project.springmvc.validator.CategoryValidator.CATEGORIES;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CategoryValidator.class)
@Documented
public @interface Category {
    String message() default "{validator.category.message} " + CATEGORIES + '.';

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}