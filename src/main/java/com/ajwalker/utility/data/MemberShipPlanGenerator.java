package com.ajwalker.utility.data;

import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.entity.MemberShipTracking;
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
		MemberShipPlan memberShipPlan6 = MemberShipPlan.builder().companyId(6L).memberType(EMemberType.YEARLY)
				.memberShipState(EMemberShipState.PAUSED).build();
		MemberShipPlan memberShipPlan7 = MemberShipPlan.builder().companyId(7L).memberType(EMemberType.YEARLY)
				.memberShipState(EMemberShipState.PAUSED).build();
		MemberShipPlan memberShipPlan8 = MemberShipPlan.builder().companyId(8L).memberType(EMemberType.YEARLY)
				.memberShipState(EMemberShipState.PAUSED).build();
		MemberShipPlan memberShipPlan9 = MemberShipPlan.builder().companyId(9L).memberType(EMemberType.YEARLY)
				.memberShipState(EMemberShipState.PAUSED).build();
		MemberShipPlan memberShipPlan10 = MemberShipPlan.builder().companyId(10L).memberType(EMemberType.YEARLY)
				.memberShipState(EMemberShipState.PAUSED).build();
		
		return List.of(memberShipPlan1, memberShipPlan2, memberShipPlan3, memberShipPlan4, memberShipPlan5,
				memberShipPlan6, memberShipPlan7, memberShipPlan8, memberShipPlan9, memberShipPlan10);
		
	}

	public static List<MemberShipTracking> generateMemberShipTracking() {
		MemberShipTracking memberShipTracking = MemberShipTracking.builder()
				.memberShipPlanId(1L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(12.32)
				.build();
		MemberShipTracking memberShipTracking2 = MemberShipTracking.builder()
				.memberShipPlanId(1L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(30.0)
				.build();

		MemberShipTracking memberShipTracking3 = MemberShipTracking.builder()
				.memberShipPlanId(2L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(30.30)
				.build();
		MemberShipTracking memberShipTracking4 = MemberShipTracking.builder()
				.memberShipPlanId(2L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(33.99)
				.build();
		MemberShipTracking memberShipTracking5 = MemberShipTracking.builder()
				.memberShipPlanId(3L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(33.99)
				.build();
		MemberShipTracking memberShipTracking6 = MemberShipTracking.builder()
				.memberShipPlanId(3L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(33.99)
				.build();
		MemberShipTracking memberShipTracking7 = MemberShipTracking.builder()
				.memberShipPlanId(4L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(19.99)
				.build();
		MemberShipTracking memberShipTracking8 = MemberShipTracking.builder()
				.memberShipPlanId(4L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(19.00)
				.build();
		MemberShipTracking memberShipTracking9 = MemberShipTracking.builder()
				.memberShipPlanId(5L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(200.00)
				.build();
		MemberShipTracking memberShipTracking10 = MemberShipTracking.builder()
				.memberShipPlanId(5L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(109.13)
				.build();
		MemberShipTracking memberShipTracking11 = MemberShipTracking.builder()
				.memberShipPlanId(6L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(13.13)
				.build();
		MemberShipTracking memberShipTracking12 = MemberShipTracking.builder()
				.memberShipPlanId(6L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(20.00)
				.build();
		MemberShipTracking memberShipTracking13 = MemberShipTracking.builder()
				.memberShipPlanId(7L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(400.00)
				.build();
		MemberShipTracking memberShipTracking14 = MemberShipTracking.builder()
				.memberShipPlanId(7L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(140.50)
				.build();
		MemberShipTracking memberShipTracking15 = MemberShipTracking.builder()
				.memberShipPlanId(8L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(140.50)
				.build();
		MemberShipTracking memberShipTracking16 = MemberShipTracking.builder()
				.memberShipPlanId(8L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(140.50)
				.build();
		MemberShipTracking memberShipTracking17 = MemberShipTracking.builder()
				.memberShipPlanId(9L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(109.13)
				.build();
		MemberShipTracking memberShipTracking18 = MemberShipTracking.builder()
				.memberShipPlanId(9L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(13.13)
				.build();
		MemberShipTracking memberShipTracking19 = MemberShipTracking.builder()
				.memberShipPlanId(10L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(20.00)
				.build();
		MemberShipTracking memberShipTracking20 = MemberShipTracking.builder()
				.memberShipPlanId(10L)
				.endDate(System.currentTimeMillis())
				.statDate(System.currentTimeMillis())
				.paymentAmount(400.00)
				.build();
		return List.of(memberShipTracking, memberShipTracking2, memberShipTracking3, memberShipTracking4, memberShipTracking5,
				memberShipTracking6, memberShipTracking7, memberShipTracking8, memberShipTracking9, memberShipTracking10,
				memberShipTracking11, memberShipTracking12, memberShipTracking13, memberShipTracking14, memberShipTracking15,
				memberShipTracking16, memberShipTracking17, memberShipTracking18, memberShipTracking19, memberShipTracking20);
	}
}