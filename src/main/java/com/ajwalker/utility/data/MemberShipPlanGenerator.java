package com.ajwalker.utility.data;

import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.utility.Enum.memberShipPlan.EMemberShipState;
import com.ajwalker.utility.Enum.memberShipPlan.EMemberType;

import java.util.List;

public class MemberShipPlanGenerator {
	
	public static List<MemberShipPlan> generateMemberShipPlans() {
		MemberShipPlan memberShipPlan1 = MemberShipPlan.builder().companyId(1L).memberType(EMemberType.YEARLY)
		                                               .memberShipState(EMemberShipState.ACTIVE).build();
		MemberShipPlan memberShipPlan2 = MemberShipPlan.builder().companyId(2L).memberType(EMemberType.MONTHLY)
		                                               .memberShipState(EMemberShipState.INACTIVE).build();
		MemberShipPlan memberShipPlan3 = MemberShipPlan.builder().companyId(3L).memberType(EMemberType.YEARLY)
		                                               .memberShipState(EMemberShipState.PAUSED).build();
		MemberShipPlan memberShipPlan4 = MemberShipPlan.builder().companyId(4L).memberType(EMemberType.MONTHLY)
		                                               .memberShipState(EMemberShipState.NONE).build();
		MemberShipPlan memberShipPlan5 = MemberShipPlan.builder().companyId(5L).memberType(EMemberType.YEARLY)
		                                               .memberShipState(EMemberShipState.PAUSED).build();
		
		return List.of(memberShipPlan1, memberShipPlan2, memberShipPlan3, memberShipPlan4, memberShipPlan5);
		
	}
}