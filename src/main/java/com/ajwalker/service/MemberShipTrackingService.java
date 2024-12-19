package com.ajwalker.service;

import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.entity.MemberShipTracking;
import com.ajwalker.repository.MemberShipTrackingRepository;
import com.ajwalker.view.VwMemberShipTrackingPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberShipTrackingService {
    final MemberShipTrackingRepository memberShipTrackingRepository;

    public List<VwMemberShipTrackingPayment> findAllPaymentAmountsByMemberIds(List<Long> memberIds ) {
        return memberShipTrackingRepository.findAmountByMemberId(memberIds);
    }


}
