package com.ajwalker.dto.request;

public record DeleteEmployeeRequestDto(
        Long userId,
        String token
) {
}
