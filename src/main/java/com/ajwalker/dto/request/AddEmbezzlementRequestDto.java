package com.ajwalker.dto.request;

public record AddEmbezzlementRequestDto(
		String description,
		String embezzlementType,
		String token
		
) {
}