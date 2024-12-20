package com.ajwalker.service;

import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.entity.MemberShipTracking;
import com.ajwalker.repository.MemberShipPlanRepository;
import com.ajwalker.repository.MemberShipTrackingRepository;
import com.ajwalker.view.VwMemberShipTrackingPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberShipTrackingService {
    final MemberShipTrackingRepository memberShipTrackingRepository;
    private final MemberShipPlanRepository memberShipPlanRepository;
    
    public List<VwMemberShipTrackingPayment> findAllPaymentAmountsByMemberIds(List<Long> memberIds ) {
        return memberShipTrackingRepository.findAmountByMemberId(memberIds);
    }
    
    
    public String findTotalPaymentByMemberShipPlanId(Long memberShipPlanId) {
        DecimalFormat df = new DecimalFormat("#.00");
       List<Double> paymentAmountList = memberShipTrackingRepository.getTotalPaymentByMemberShipPlanId(memberShipPlanId);
       Double totalPaymentAmount = 0.0;
       for (Double paymentAmount : paymentAmountList) {
           totalPaymentAmount += paymentAmount;
       }
       return df.format(totalPaymentAmount);
    }
}