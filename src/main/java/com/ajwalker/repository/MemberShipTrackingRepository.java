package com.ajwalker.repository;

import com.ajwalker.entity.MemberShipTracking;
import com.ajwalker.view.VwMemberShipTrackingPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberShipTrackingRepository  extends JpaRepository<MemberShipTracking, Long> {
    @Query(value = "SELECT new com.ajwalker.view.VwMemberShipTrackingPayment(M.memberShipPlanId,M.paymentAmount) " +
            "FROM MemberShipTracking M " +
            "WHERE M.memberShipPlanId IN(?1)")
    List<VwMemberShipTrackingPayment> findAmountByMemberId(List<Long> memberShipTrackingPayments);

}
