package com.ajwalker.dto.request;

public record IUpdateEmployeeRequestDto (
        Long companyId,
        Long userId,
        String avatar,
        String email,
        String userState,
        String address,
        String annualSalary,
        String dateOfBirth,
        String dateOfEmployment,
        String dateOfTermination,
        String firstName,
        String lastName,
        String gender,
        String identityNumber,
        String socialSecurityNumber,
        String mobileNumber,
        String position
){}

