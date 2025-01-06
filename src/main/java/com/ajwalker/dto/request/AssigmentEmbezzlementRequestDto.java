package com.ajwalker.dto.request;

public record AssigmentEmbezzlementRequestDto(
		Long embezzlementId,
		String firstName,
		String lastName,
		String email,
		String token
		
) {
}