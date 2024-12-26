package com.ajwalker.dto.response;

import com.ajwalker.utility.Enum.user.EEmploymentStatus;

public record EmployeesResponseDto(
        Long companyId,
        Long userId,
        String avatar,
        String email,
        String address,
        Double annualSalary,
        Long dateOfBirth,
        Long dateOfEmployment,
        Long dateOfTermination,
        String firstName,
        String lastName,
        String gender,
        String identityNumber,
        String socialSecurityNumber,
        String mobileNumber,
        String position,
        String employmentStatus

){}