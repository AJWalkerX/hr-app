package com.ajwalker.dto.request;

import java.time.LocalTime;

public record UpdateShiftRequestDto(
        String token,
        Long shiftId,
        String shiftName,
        LocalTime shiftStart,
        LocalTime shiftEnd
) {
}
