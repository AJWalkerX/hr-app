package com.ajwalker.dto.response;

public record CommentDetailsResponseDto(
		Long userId,
		Long commentId,
		String companyLogo,
		String companyName,
		String firstName,
		String lastName,
		String position,
		String content,
		String description,
		String companyRegion,
		String companyType
) {
}