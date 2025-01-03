package com.ajwalker.dto.response;

import java.time.LocalDate;

public record ManagerSpendingResponseDto(
		Long companyId,
		String avatar,
		String firstName,
		String lastName,
		String position,
		LocalDate spendingDate,
		String description,
		String spendingType
		
) {
}