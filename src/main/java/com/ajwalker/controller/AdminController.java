package com.ajwalker.controller;


import com.ajwalker.dto.request.AdminLoginRequestDto;
import com.ajwalker.dto.request.UserAuthorisationDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.CompanyCustomersInfoResponseDto;
import com.ajwalker.dto.response.UserOnWaitInfoResponseDto;
import com.ajwalker.service.AdminService;
import com.ajwalker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(ADMIN)
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {
	private final AdminService adminService;
	private final UserService userService;
	
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
	public ResponseEntity<BaseResponse<List<CompanyCustomersInfoResponseDto>>> listAllCustomers(){ //Hesabi onaylanan herkes customer
		return ResponseEntity.ok(BaseResponse.<List<CompanyCustomersInfoResponseDto>>builder()
						.message("Tum kullanicilarin listesi")
						.code(200)
						.success(true)
						.data(adminService.getAllCustomers())
				.build());
	}

	@GetMapping(LISTUSERONWAIT)
	public ResponseEntity<BaseResponse<List<UserOnWaitInfoResponseDto>>> listAllUserOnWait(){ //Denied yada in review da olan userlar
		return ResponseEntity.ok(BaseResponse.<List<UserOnWaitInfoResponseDto>>builder()
				.message("Tum kullanicilarin listesi")
				.code(200)
				.success(true)
				.data(adminService.getAllOnWaitCustomers())
				.build());
	}

	@PostMapping(USERAUTHORISATION)
	public ResponseEntity<BaseResponse<Boolean>> userAuthorisation(@RequestBody UserAuthorisationDto dto){
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				.message("Kullanici onaylama/ reddetme islemi tamamlanmistir")
				.code(200)
				.success(true)
				.data(adminService.userAuthorisation(dto))
				.build());
	}

}