package com.ajwalker.service;

import com.ajwalker.dto.request.AdminLoginRequestDto;
import com.ajwalker.dto.response.CompanyCustomersInfoResponseDto;
import com.ajwalker.entity.Admin;
import com.ajwalker.entity.Company;
import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.AdminRepository;
import com.ajwalker.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final AdminRepository adminRepository;
	private final JwtManager jwtManager;
	private final PasswordEncoder passwordEncoder;
	private final CompanyService companyService;
	private final MemberShipPlanService memberShipPlanService;

	public String adminLogin(AdminLoginRequestDto dto) {
		Optional<Admin> adminOptional = adminRepository.findOptionalByUsername(dto.username());
		if (adminOptional.isEmpty() || !passwordEncoder.matches(dto.password(), adminOptional.get().getPassword())){
			throw new HRAppException(ErrorType.INVALID_ADMIN);
		}
		String token = jwtManager.createToken(adminOptional.get().getId());
		return token;
	}

    public List<CompanyCustomersInfoResponseDto> getAllCustomers() {
		List<Company> allUserByUserState = companyService.findAll();
		return allUserByUserState.stream().map(company -> {

			MemberShipPlan memberShipPlan = memberShipPlanService
					.findById(company.getId());

			return new CompanyCustomersInfoResponseDto(
					company.getCompanyLogo(),
					company.getCompanyName(),
					company.getCompanyMail(),
					company.getCompanyAddress(),
					company.getTelNo(),
					company.getCompanyType().toString(),
					company.getRegion().toString(),
					memberShipPlan.getMemberType().toString(),
					memberShipPlan.getMemberShipState().toString()

			);

		}).collect(Collectors.toList());
    }
}