package com.ajwalker.dto.response;

public record CompanyCustomersInfoResponseDto (
        Long companyId,
        String companyLogo,
        String companyName,
        String companyMail,
        String companyAddress,
        String companyTelNo,
        String companyType,
        String companyRegion,
        String memberShipState,
        String totalPaymentAmount
        


){
}