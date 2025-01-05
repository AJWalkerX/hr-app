package com.ajwalker.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ManagerSpendingResponseDto(
		Long userId,
		Long companyId,
		String avatar,
		String firstName,
		String lastName,
		List<SpendingDetails> spendingDetails
) {
	public record SpendingDetails(
			LocalDate spendingDate,
			String description,
			String spendingType,
			Long spendingId,
			Double billAmount
	) {}
}