package com.ajwalker.dto.request;

import java.time.LocalTime;

public record CreateShiftRequestDto(
        String token,
        String shiftName,
        LocalTime shiftStart,
        LocalTime shiftEnd
) {
}
