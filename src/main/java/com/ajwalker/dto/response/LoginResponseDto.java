package com.ajwalker.dto.response;

public record LoginResponseDto(
        String token,
        Boolean isFirstLogin
) {
}
