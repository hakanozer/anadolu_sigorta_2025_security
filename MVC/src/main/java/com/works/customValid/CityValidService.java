package com.works.customValid;

import com.works.repositories.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CityValidService implements ConstraintValidator<CityValid, String> {

    private final CustomerRepository customerRepository;
    String[] cities = {"istanbul", "ankara", "izmir"};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean status = Arrays.asList(cities).contains(value);
        return status;
    }

}
