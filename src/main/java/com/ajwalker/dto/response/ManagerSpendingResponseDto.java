package com.ajwalker.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ManagerSpendingResponseDto(
		Long userId,
		Long companyId,
		String avatar,
		String firstName,
		String lastName,
		String position,
		List<SpendingDetailDto> spendingDetails
		
) {
}