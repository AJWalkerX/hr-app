package com.ajwalker.dto.response;

public record EmbezzlementResponseDto(
	Long userId,
	Long companyId,
	String description,
	String embezzlementType,
	String embezzlementState,
	String avatar,
	String firstName,
	String lastName
) {
}