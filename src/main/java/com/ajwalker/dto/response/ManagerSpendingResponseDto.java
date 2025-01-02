package com.ajwalker.dto.response;

import java.time.LocalDate;

public record ManagerSpendingResponseDto(
		String avatar,
		String firstName,
		String lastName,
		String position,
		LocalDate spendingDate,
		String description,
		String spendingType
		
) {
}