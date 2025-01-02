package com.ajwalker.dto.response;

import java.time.LocalDate;

public record PersonalSpendingResponseDto(
		LocalDate spendingDate,
		String description,
		Double billAmount,
		String spendingType,
		String spendingState
) {
}