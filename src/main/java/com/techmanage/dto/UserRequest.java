package com.techmanage.dto;

import com.techmanage.model.UserType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserRequest(
        @NotBlank               String fullName,
        @Email @NotBlank        String email,
        @Pattern(regexp = "^\\+\\d{1,3}\\s\\d{2}\\s\\d{4,5}-\\d{4}$")
        String phone,
        @Past                   LocalDate birthDate,
        @NotNull                UserType userType
) { }
