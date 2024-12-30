package com.ajwalker.dto.request;

public record AddCommentRequestDto(
		Long userId,
		String content,
		String description
) {
}