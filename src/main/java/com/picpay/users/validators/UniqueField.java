package com.picpay.users.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { UniqueFieldValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueField {

    String message() default "{com.picpay.beanvalidation.uniquefield}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String domainAttribute();
    Class<?> klass();
}
