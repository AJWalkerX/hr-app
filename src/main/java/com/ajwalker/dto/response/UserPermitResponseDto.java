package com.ajwalker.dto.response;

import java.time.LocalDate;

public record UserPermitResponseDto(
		
		Long userId,
		String avatar,
		String firstName,
		String lastName,
		String position,
		LocalDate beginDate,
		LocalDate endDate,
		String description,
		String holidayType,
		String holidayState
		
) {
}