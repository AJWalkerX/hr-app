package com.ajwalker.repository;

import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.entity.PersonalDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberShipPlanRepository  extends JpaRepository<MemberShipPlan, Long> {

    Optional<MemberShipPlan> findByCompanyId(Long companyId);
}
