package com.ajwalker.dto.request;

import com.ajwalker.utility.Enum.user.EPosition;

import java.time.LocalDate;

public record UpdateUserProfileInformationRequestDto(
		Long userId,
		String avatar,
		String firstName  ,
		String lastName ,
		String identityNumber,
		LocalDate dateOfBirth,
		String mobileNumber,
		String address,
		String gender,
		String email,
		String position,
		LocalDate dateOfEmployment,
		String socialSecurityNumber
) {
}