package com.ajwalker.dto.response;

public record EmbezzlementResponseDto(
	Long embezzlementId,
	Long companyId,
	String description,
	String embezzlementType,
	String embezzlementState
) {
}