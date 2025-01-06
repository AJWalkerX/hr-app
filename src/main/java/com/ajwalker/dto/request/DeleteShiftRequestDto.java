package com.ajwalker.dto.request;

public record DeleteShiftRequestDto(
        Long shiftId,
        String token
) {
}
