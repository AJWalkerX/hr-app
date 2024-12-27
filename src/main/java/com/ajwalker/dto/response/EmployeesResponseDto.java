package com.ajwalker.dto.response;

import com.ajwalker.utility.Enum.user.EEmploymentStatus;

import java.time.LocalDate;

public record EmployeesResponseDto(
        Long companyId,
        Long userId,
        String avatar,
        String email,
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
        String position,
        String employmentStatus

){}