package com.ajwalker.dto.response;

import com.ajwalker.utility.Enum.user.EGender;
import com.ajwalker.utility.Enum.user.EPosition;

import java.time.LocalDate;

public record GetUserProfileInfoDto(
		String avatar,
		String firstName  ,
		String lastName ,
		String identityNumber,
		LocalDate dateOfBirth,
		String mobileNumber,
		String address,
		String gender,
		String email,
		EPosition position,
		LocalDate dateOfEmployment,
		String socialSecurityNumber,
		String companyName
		
		) {
}