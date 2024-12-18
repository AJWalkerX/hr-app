package com.ajwalker.service;


import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.repository.MemberShipPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberShipPlanService {
    private final MemberShipPlanRepository memberShipPlanRepository;


    public MemberShipPlan findById(Long id) {
        return memberShipPlanRepository.findById(id).get();
    }

}
