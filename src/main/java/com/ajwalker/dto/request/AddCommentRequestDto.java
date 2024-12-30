package com.ajwalker.dto.request;

public record AddCommentRequestDto(
	    String token,
		String content,
		String description
) {
}