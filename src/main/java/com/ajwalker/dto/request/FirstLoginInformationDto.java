package com.ajwalker.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FirstLoginInformationDto(
        String token,
        String firstName,
        String lastName,
        String identityNumber,
        LocalDate dateOfBirth,
        String mobile,
        String address,
        String mail,
        String gender,
        String companyName,
        String companyMail,
        String companyPhoneNumber,
        String companyAddress,
        String companySector,
        String companyRegion,
        //TODO: Sirket logosu eklenecek!
        LocalDate dateOfBeginToWork,
        String socialSecurityNumber,
        String position
) {
}
