package com.ajwalker.repository;

import com.ajwalker.entity.MemberShipPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberShipPlanRepository  extends JpaRepository<MemberShipPlan, Long> {

    Optional<MemberShipPlan> findByCompanyId(Long companyId);

    @Query("SELECT M FROM MemberShipPlan M WHERE  M.companyId IN(?1)")
    List<MemberShipPlan> findAllByCompanyIds(List<Long> companyIdList);
    
 
}