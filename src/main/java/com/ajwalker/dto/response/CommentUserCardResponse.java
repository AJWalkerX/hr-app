package com.ajwalker.dto.response;

public record CommentUserCardResponse(
        String firstName,
        String lastName,
        String companyName,
        String position,
        String avatar
){
}
