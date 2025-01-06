package com.ajwalker.dto.response;

public record CommentUserCardResponse(
		Long commentId,
        String firstName,
        String lastName,
        String companyName,
        String position,
        String avatar
){
}