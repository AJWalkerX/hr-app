package com.ajwalker.dto.response;

import com.ajwalker.utility.Enum.user.EPosition;

public record UserOnWaitInfoResponseDto(
        Long userId,
   String firstName,
   String lastName,
   String email,
   String position,
   String companyName
) {}
