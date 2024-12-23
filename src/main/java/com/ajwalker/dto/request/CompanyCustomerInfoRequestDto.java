package com.ajwalker.dto.request;

public record CompanyCustomerInfoRequestDto(
		Long companyId,
		String companyLogo,
		String companyName,
		String companyMail,
		String companyAddress,
		String companyTelNo,
		String companyType,
		String companyRegion
	
) {
}