package com.ajwalker.dto.request;

import java.time.LocalDate;
import java.util.List;

public record AssignShiftRequestDto(
        String token,
        String email,
        String firstName,
        String lastName,
        Long shiftId,
        LocalDate startDate,
        LocalDate endDate
) {
}
