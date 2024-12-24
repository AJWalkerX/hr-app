package com.ajwalker.controller;


import com.ajwalker.dto.request.AdminLoginRequestDto;
import com.ajwalker.dto.request.UserAuthorisationDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.CompanyCustomersInfoResponseDto;
import com.ajwalker.dto.response.UserOnWaitInfoResponseDto;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.service.AdminService;
import com.ajwalker.service.UserService;
import com.ajwalker.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(ADMIN)
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {
	private final AdminService adminService;
	private final UserService userService;
	private final JwtManager jwtManager;

	@PostMapping(ADMINLOGIN)
	public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid AdminLoginRequestDto dto){
		String token = adminService.adminLogin(dto);
		return ResponseEntity.ok(BaseResponse.<String>builder()
		                                     .success(true)
		                                     .message("Admin Girişi Basarili :)")
		                                     .data(token)
		                                     .code(200)
		                                     .build());
	}

	@GetMapping(LISTCUSTOMER)
	public ResponseEntity<BaseResponse<List<CompanyCustomersInfoResponseDto>>> listAllCustomers(@RequestParam(name = "token") String token){ //Hesabi onaylanan herkes customer
		Optional<Long> optionalAdminId = jwtManager.verifyToken(token);
		if(optionalAdminId.isEmpty()){
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		return ResponseEntity.ok(BaseResponse.<List<CompanyCustomersInfoResponseDto>>builder()
						.message("Tum kullanicilarin listesi")
						.code(200)
						.success(true)
						.data(adminService.getAllCustomers())
				.build());
	}

	@GetMapping(LISTUSERONWAIT)
	public ResponseEntity<BaseResponse<List<UserOnWaitInfoResponseDto>>> listAllUserOnWait(@RequestParam(name = "token") String token){ //Denied yada in review da olan userlar
		Optional<Long> optionalAdminId = jwtManager.verifyToken(token);
		if(optionalAdminId.isEmpty()){
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		return ResponseEntity.ok(BaseResponse.<List<UserOnWaitInfoResponseDto>>builder()
				.message("Tum kullanicilarin listesi")
				.code(200)
				.success(true)
				.data(adminService.getAllOnWaitCustomers())
				.build());
	}

	@PostMapping(USERAUTHORISATION)
	public ResponseEntity<BaseResponse<Boolean>> userAuthorisation(@RequestBody UserAuthorisationDto dto){
		Optional<Long> optionalAdminId = jwtManager.verifyToken(dto.adminToken());
		if(optionalAdminId.isEmpty()){
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				.message("Kullanici onaylama/ reddetme islemi tamamlanmistir")
				.code(200)
				.success(true)
				.data(adminService.userAuthorisation(dto))
				.build());
	}

}