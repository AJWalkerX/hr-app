package com.ajwalker.view;

public record VwMemberShip(
        Long id,
        Long companyId,
        String memberType,
        String memberShipState,
        Double totalPaymentAmount
) {
}
