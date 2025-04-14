package com.works.entities.dto;

import com.works.customValid.CityValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


/**
 * DTO for {@link com.works.entities.Customer}
 */
@Data
public class CustomerLoginDto {
    @NotNull
    @Email
    @NotEmpty
    String email;

    @NotNull
    @Size(min = 5, max = 15)
    @NotEmpty
    String password;

    @CityValid
    @NotNull
    @NotEmpty
    String city;
}