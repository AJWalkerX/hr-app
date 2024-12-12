package com.ajwalker.controller;


import com.ajwalker.dto.request.AdminLoginRequestDto;
import com.ajwalker.dto.request.DologinRequestDto;
import com.ajwalker.dto.request.UserAuthorisationDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.User;
import com.ajwalker.service.AdminService;
import com.ajwalker.service.UserService;
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
	public ResponseEntity<BaseResponse<List<User>>> listAllCustomers(){ //Hesabi onaylanan herkes customer
		return ResponseEntity.ok(BaseResponse.<List<User>>builder()
						.message("Tum kullanicilarin listesi")
						.code(200)
						.success(true)
						.data(userService.getAllCustomers())
				.build());
	}

	@GetMapping(LISTUSERONWAIT)
	public ResponseEntity<BaseResponse<List<User>>> listAllUserOnWait(){ //Denied yada in review da olan userlar
		return ResponseEntity.ok(BaseResponse.<List<User>>builder()
				.message("Tum kullanicilarin listesi")
				.code(200)
				.success(true)
				.data(userService.getAllUserOnWait())
				.build());
	}

	@PostMapping(USERAUTHORISATION)
	public ResponseEntity<BaseResponse<User>> userAuthorisation(@RequestBody UserAuthorisationDto dto){
		return ResponseEntity.ok(BaseResponse.<User>builder()
				.message("Kullanici onaylama/ reddetme islemi tamamlanmistir")
				.code(200)
				.success(true)
				.data(userService.userAuthorisation(dto))
				.build());
	}

}