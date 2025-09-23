package br.com.fiap.bank_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgencyValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Agency {
    String message() default "{account.agency.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
