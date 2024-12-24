package com.ajwalker.dto.request;

public record UserAuthorisationDto(
        Long userId,
        String answer,
        String adminToken
) {
}
