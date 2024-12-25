package com.ajwalker.dto.request;

public record WorkHolidayDto(
		Long userId,
		String firstName,
		String lastName,
		String mobileNumber,
		String email,
		Long beginDate,
		Long endDate,
		String holidayType,
		String description
		
) {
}