package com.ajwalker.dto.request;

import java.time.LocalDate;

public record IUpdateEmployeeRequestDto (
        Long companyId,
        Long userId,
        String avatar,
        String email,
        String userState,
        String address,
        Double annualSalary,
        LocalDate dateOfBirth,
        LocalDate dateOfEmployment,
        LocalDate dateOfTermination,
        String firstName,
        String lastName,
        String gender,
        String identityNumber,
        String socialSecurityNumber,
        String mobileNumber,
        String position
){}