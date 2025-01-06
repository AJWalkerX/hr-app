package com.ajwalker.dto.response;

public record EmbezzlementResponseDto(
	Long companyId,
	String description,
	String embezzlementType,
	String embezzlementState
) {
}