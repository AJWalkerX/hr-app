package com.ajwalker.dto.request;

public record DeleteEmployeeRequestDto(
        String token,
        Long userId
) {
}
