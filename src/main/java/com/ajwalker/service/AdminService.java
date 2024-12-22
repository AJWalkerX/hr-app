package com.ajwalker.service;

import com.ajwalker.dto.request.AdminLoginRequestDto;
import com.ajwalker.dto.request.UserAuthorisationDto;
import com.ajwalker.dto.response.CompanyCustomersInfoResponseDto;
import com.ajwalker.entity.*;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.AdminRepository;
import com.ajwalker.utility.Enum.user.EUserAuthorisation;
import com.ajwalker.utility.JwtManager;
import com.ajwalker.view.VwMemberShip;
import com.ajwalker.view.VwMemberShipTrackingPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final AdminRepository adminRepository;
	private final JwtManager jwtManager;
	private final PasswordEncoder passwordEncoder;
	private final CompanyService companyService;
	private final MemberShipPlanService memberShipPlanService;
	private final MemberShipTrackingService memberShipTrackingService;
	private final UserService userService;

	public String adminLogin(AdminLoginRequestDto dto) {
		Optional<Admin> adminOptional = adminRepository.findOptionalByUsername(dto.username());
		if (adminOptional.isEmpty() || !passwordEncoder.matches(dto.password(), adminOptional.get().getPassword())){
			throw new HRAppException(ErrorType.INVALID_ADMIN);
		}
        return jwtManager.createToken(adminOptional.get().getId());
	}

    public List<CompanyCustomersInfoResponseDto> getAllCustomers() {
		DecimalFormat df = new DecimalFormat("#.00");
// Fetch the top 100 companies
		List<Company> companyList = companyService.findAllTop100();
		List<Long> companyIdList = companyList.stream().map(Company::getId).toList();

		// Fetch all membership plans for these companies
		List<MemberShipPlan> memberShipPlanList = memberShipPlanService.findAllByCompanyId(companyIdList);
		List<Long> memberShipPlanIdList = memberShipPlanList.stream().map(MemberShipPlan::getId).toList();

		// Fetch all payment amounts for the membership plans
		List<VwMemberShipTrackingPayment> memberShipTrackingPaymentList = memberShipTrackingService.findAllPaymentAmountsByMemberIds(memberShipPlanIdList);

		// Aggregate payment amounts by membership plan ID
		Map<Long, Double> memberShipTrackingPaymentMap = memberShipTrackingPaymentList.stream()
				.collect(Collectors.groupingBy(
						VwMemberShipTrackingPayment::memberShipPlanId,
						Collectors.summingDouble(VwMemberShipTrackingPayment::paymentAmount)
				));

		// Convert membership plans to VwMemberShip DTOs
		List<VwMemberShip> vwMemberShipList = memberShipPlanList.stream()
				.map(memberShip -> new VwMemberShip(
						memberShip.getId(),
						memberShip.getCompanyId(),
						memberShip.getMemberType().toString(),
						memberShip.getMemberShipState().toString(),
						memberShipTrackingPaymentMap.get(memberShip.getId()) // Get total payment amount
				))
				.toList();

		// Map companies and their membership information to DTOs
		return companyList.stream()
				.flatMap(company -> vwMemberShipList.stream()
						.filter(vwMemberShip -> vwMemberShip.companyId().equals(company.getId())) // Filter matching company ID
						.map(vwMemberShip -> new CompanyCustomersInfoResponseDto(
								company.getId(),
								company.getCompanyLogo(),
								company.getCompanyName(),
								company.getCompanyMail(),
								company.getCompanyAddress(),
								company.getTelNo(),
								company.getCompanyType().toString(),
								company.getRegion().toString(),
								vwMemberShip.memberShipState(),
								df.format(vwMemberShip.totalPaymentAmount())

						))
				)
				.collect(Collectors.toList());
    }
	public Boolean userAuthorisation(UserAuthorisationDto dto) {
		Optional<User> userOptional = userService.findById(dto.userId());
		if(userOptional.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		User user = userOptional.get();
		Optional<Company> companyOptional = companyService.findById(user.getCompanyId());
		if (companyOptional.isEmpty()){
			throw new HRAppException(ErrorType.NOTFOUND_COMPANY);
		}
		Company company = companyOptional.get();
		if(dto.answer().equalsIgnoreCase(EUserAuthorisation.ACCEPT.toString())) {
			userService.updateUserToActive(user);
			companyService.updateCompanyToAccepted(company);
		}
		if(dto.answer().equalsIgnoreCase(EUserAuthorisation.DENY.toString())) {
			userService.updateUserToDenied(user);
			companyService.updateCompanyToDenied(company);
		}
		return true;
	}
}