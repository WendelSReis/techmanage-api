package com.techmanage.dto;

import com.techmanage.model.UserType;

import java.time.LocalDate;

public record UserResponse(
        Long        id,
        String      fullName,
        String      email,
        String      phone,
        LocalDate   birthDate,
        UserType    userType
) { }
