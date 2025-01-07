package com.ajwalker.dto.response;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public record EmbezzlementResponseDto(
	Long embezzlementId,
	Long companyId,
	Long userId,
	String description,
	String embezzlementType,
	String embezzlementState,
	String title,
	UserDetails userDetails
) {
	public  record UserDetails(
			String avatar,
			String firstName,
			String lastName
	){}
}