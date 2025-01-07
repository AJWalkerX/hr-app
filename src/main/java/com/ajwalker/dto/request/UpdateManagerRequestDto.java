package com.ajwalker.dto.request;

import com.ajwalker.utility.Enum.company.ECompanyType;
import com.ajwalker.utility.Enum.user.EGender;

import java.time.LocalDate;

public record UpdateManagerRequestDto(
		String token,
		String avatar,
		String identityNumber,
		LocalDate dateOfBirth,
		String mobileNumber,
		String address,
		String gender,
		LocalDate dateOfEmployment,
		String socialSecurityNumber,
		
		String companyMail,
		String companyLogo,
		String companyType,
		String region,
		String companyAddress,
		String telNo
) {
}