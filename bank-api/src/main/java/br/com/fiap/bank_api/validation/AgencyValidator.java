package br.com.fiap.bank_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgencyValidator implements ConstraintValidator<Agency, String> {

    private static final String REGEX = "^[0-9]{4}(-[0-9])?$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.matches(REGEX);
    }
}
