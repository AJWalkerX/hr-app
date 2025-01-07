package com.ajwalker.dto.response;

import java.time.LocalDate;

public record PersonalEmbezzlementResponseDto(
		String title,
		String description,
		LocalDate assignmentDate
) {
}