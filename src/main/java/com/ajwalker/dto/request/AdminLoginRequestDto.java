package com.ajwalker.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AdminLoginRequestDto(
		@NotNull
		String username,
		@NotNull
		String password
) {
}