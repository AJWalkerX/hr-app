package com.ajwalker.dto.response;

import com.ajwalker.utility.Enum.user.EGender;
import com.ajwalker.utility.Enum.user.EPosition;

public record GetUserProfileInfoDto(
		String firstName  ,
		String lastName ,
		String identityNumber,
		Long dateOfBirth,
		String mobileNumber,
		String address,
		EGender gender,
		String email,
		EPosition position,
		Long dateOfEmployment,
		String socialSecurityNumber
		) {
}