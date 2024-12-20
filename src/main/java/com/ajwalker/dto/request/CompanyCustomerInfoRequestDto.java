package com.ajwalker.dto.request;

public record CompanyCustomerInfoRequestDto(
		Long companyId,
		String companyLogo,
		String companyName,
		String companyMail,
		String companyAddress,
		String telNo,
		String companyType,
		String region,
		String memberType
) {
}