package com.ajwalker.dto.request;

import com.ajwalker.utility.Enum.personalSpending.ESpendingType;

import java.time.LocalDate;

public record AddPersonalSpendingRequestDto(
		String token,
		String description,
		Double billAmount,
		LocalDate spendingDate,
		String spendingType
) {
}