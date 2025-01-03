package com.ajwalker.dto.request;

import java.time.LocalDate;
import java.util.List;

public record AssignShiftRequestDto(
        String token,
        List<Long> userId,
        Long shiftId,
        LocalDate startDate,
        LocalDate endDate
) {
}
