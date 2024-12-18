package com.ajwalker.service;


import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.repository.MemberShipPlanRepository;
import com.ajwalker.utility.Enum.memberShipPlan.EMemberShipState;
import com.ajwalker.utility.Enum.memberShipPlan.EMemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberShipPlanService {
    private final MemberShipPlanRepository memberShipPlanRepository;


    public MemberShipPlan findById(Long id) {
        return memberShipPlanRepository.findById(id).get();
    }

    public void createMemberShip(Long companyId) {
        MemberShipPlan memberShipPlan = MemberShipPlan.builder()
                .companyId(companyId)
                .memberType(EMemberType.NONE)
                .memberShipState(EMemberShipState.NONE)
                .build();
        memberShipPlanRepository.save(memberShipPlan);
    }
}
