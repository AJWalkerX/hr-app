package com.ajwalker.dto.response;

import java.time.LocalDate;

public record ViewYourUserPermitResponseDto(
		
		Long userId,
		Long workHolidayId,
		LocalDate beginDate,
		LocalDate endDate,
		String description,
		String holidayType,
		String holidayState
) {
}