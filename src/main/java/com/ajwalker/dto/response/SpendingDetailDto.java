package com.ajwalker.dto.response;

import java.time.LocalDate;

public record SpendingDetailDto(
		LocalDate spendingDate,
		String description,
		String spendingType
		
) {
}