package com.ajwalker.dto.response;

public record GetEmbezzlementDetailsResponseDto(
		Long embezzlementId,
		Long userId,
		String description,
		String embezzlementType,
		String embezzlementState,
		String avatar,
		String firstName,
		String lastName
) {
}