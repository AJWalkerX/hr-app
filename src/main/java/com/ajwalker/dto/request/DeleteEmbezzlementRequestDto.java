package com.ajwalker.dto.request;

public record DeleteEmbezzlementRequestDto(
		Long embezzlementId,
		String token
) {
}