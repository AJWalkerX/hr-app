package com.ajwalker.dto.response;

public record UserPermitResponseDto(
		
		Long userId,
		String avatar,
		String firstName,
		String lastName,
		String position,
		Long beginDate,
		Long endDate,
		String description,
		String holidayType,
		String holidayState
		
) {
}