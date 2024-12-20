package com.ajwalker.controller;

import com.ajwalker.dto.request.CompanyCustomerInfoRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.CommentCardResponseDto;
import com.ajwalker.dto.response.CompanyCustomersInfoResponseDto;
import com.ajwalker.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping(COMPANY)
public class CompanyController {
	private final CompanyService companyService;
	
	@PutMapping(UPDATE_COMPANY)
	public ResponseEntity<BaseResponse<CompanyCustomersInfoResponseDto>> updateCompany(CompanyCustomerInfoRequestDto dto){
		return ResponseEntity.ok(BaseResponse.<CompanyCustomersInfoResponseDto>builder()
		                                     .message("Şirket bilgileri başarıyla güncellendi.")
		                                     .code(200)
		                                     .success(true)
		                                     .data(companyService.updateCompany(dto))
		                                     .build());
	}
}