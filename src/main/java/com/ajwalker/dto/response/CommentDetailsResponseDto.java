package com.ajwalker.dto.response;

import jakarta.persistence.Lob;

public record CommentDetailsResponseDto(
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