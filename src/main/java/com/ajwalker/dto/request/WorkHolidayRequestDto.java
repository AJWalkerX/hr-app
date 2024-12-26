package com.ajwalker.dto.request;

import java.time.LocalDate;

public record WorkHolidayRequestDto(
		LocalDate beginDate,
		LocalDate endDate,
		String holidayType,
		String description,
		String token
		
) {
}