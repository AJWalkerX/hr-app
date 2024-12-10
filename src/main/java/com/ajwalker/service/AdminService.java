package com.ajwalker.service;

import com.ajwalker.dto.request.AdminLoginRequestDto;
import com.ajwalker.entity.Admin;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.AdminRepository;
import com.ajwalker.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final AdminRepository adminRepository;
	private final JwtManager jwtManager;
	
	public String adminLogin(AdminLoginRequestDto dto) {
		Optional<Admin> adminOptional = adminRepository.findOptionalByUsernameAndPassword(dto.username(), dto.password());
		if (adminOptional.isEmpty())
			throw new HRAppException(ErrorType.INVALID_ADMIN);
		String token = jwtManager.createToken(adminOptional.get().getId());
		return token;
	}
}