package com.ajwalker.dto.request;

public record HolidayAuthorizeRequestDto(
    Long userId,
    Long workHolidayId,
    String answer,
    String token
) {
}
