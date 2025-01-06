package com.ajwalker.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record MyShiftResponseDto(
        String shiftName,
        LocalTime startTime,
        LocalTime endTime,
        LocalDate StartDate,
        LocalDate endDate
) {
}
