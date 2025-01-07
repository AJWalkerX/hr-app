package com.ajwalker.dto.response;

public record EmbezzlementResponseDto(
	Long embezzlementId,
	Long companyId,
	Long userId,
	String description,
	String embezzlementType,
	String embezzlementState
) {
}