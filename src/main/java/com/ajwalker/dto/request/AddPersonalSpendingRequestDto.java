package com.ajwalker.dto.request;

import com.ajwalker.utility.Enum.personalSpending.ESpendingType;

import java.time.LocalDate;

public record AddPersonalSpendingRequestDto(
		Long userId,
		String description,
		Double billAmount,
		LocalDate spendingDate,
		String spendingType
) {
}