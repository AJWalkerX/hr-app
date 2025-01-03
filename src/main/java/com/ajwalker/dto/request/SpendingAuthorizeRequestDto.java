package com.ajwalker.dto.request;

public record SpendingAuthorizeRequestDto(
		Long userId,
		Long spendingId,
		String answer,
		String token
		
) {
}