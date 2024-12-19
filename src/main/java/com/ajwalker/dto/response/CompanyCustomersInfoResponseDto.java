package com.ajwalker.dto.response;

public record CompanyCustomersInfoResponseDto (
        Long companyId,
        String companyLogo,
        String companyName,
        String companyMail,
        String companyAddress,
        String telNo,
        String companyType,
        String region,
        String memberType,
        String memberShipState,
        String totalPaymentAmount


){
}
