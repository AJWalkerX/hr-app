package com.ajwalker.dto.request;

import java.time.LocalDate;

public record WorkHolidayRequestDto(
		Long userId,
		LocalDate beginDate,
		LocalDate endDate,
		String holidayType,
		String description
		
) {
}