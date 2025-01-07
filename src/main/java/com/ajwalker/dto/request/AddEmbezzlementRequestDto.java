package com.ajwalker.dto.request;

public record AddEmbezzlementRequestDto(
		String title,
		String description,
		String embezzlementType,
		String token
		
) {
}