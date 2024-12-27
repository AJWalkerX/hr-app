package com.ajwalker.dto.response;

import com.ajwalker.utility.Enum.user.EPosition;

public record LoginResponseDto(
        String token,
        Boolean isFirstLogin,
        String position

) {
}
