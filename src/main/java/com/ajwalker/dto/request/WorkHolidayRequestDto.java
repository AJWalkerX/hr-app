package com.ajwalker.dto.request;

import java.time.LocalDate;

public record WorkHolidayRequestDto(
		Long userId,
		String firstName,
		String lastName,
		String mobileNumber,
		String email,
		LocalDate beginDate,
		LocalDate endDate,
		String holidayType,
		String description
		
) {
}