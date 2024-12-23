package com.ajwalker.dto.response;

public record UserPermitResponseDto(
		
		Long userId,
		String avatar,
		String firstName,
		String lastName,
		String position,
		String employmentStatus
) {
}