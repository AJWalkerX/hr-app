package com.ajwalker.controller;


import com.ajwalker.dto.request.AdminLoginRequestDto;
import com.ajwalker.dto.request.DologinRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.service.AdminService;
import com.ajwalker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(ADMIN)
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {
	private final AdminService adminService;
	
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
	
}