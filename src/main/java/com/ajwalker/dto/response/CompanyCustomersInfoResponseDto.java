package com.ajwalker.dto.response;

public record CompanyCustomersInfoResponseDto (

        String companyLogo,
        String companyName,
        String companyMail,
        String companyAddress,
        String telNo,
        String companyType,
        String region,
        String memberType,
        String memberShipState

//   TODO     Long memberStartDate
//        Long memberEndDate
//        Double paymentAmount ekleyeceğiz!!!

){
}
