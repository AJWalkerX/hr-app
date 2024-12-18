package com.ajwalker.repository;

import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.entity.PersonalDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberShipPlanRepository  extends JpaRepository<MemberShipPlan, Long> {

}
