package com.ajwalker.controller;

import com.ajwalker.dto.request.CompanyCustomerInfoRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.CommentCardResponseDto;
import com.ajwalker.dto.response.CompanyCustomersInfoResponseDto;
import com.ajwalker.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping(COMPANY)
public class CompanyController {
	private final CompanyService companyService;
	
	@PutMapping(UPDATE_COMPANY)
	public ResponseEntity<BaseResponse<CompanyCustomersInfoResponseDto>> updateCompany(@RequestBody @Valid CompanyCustomerInfoRequestDto dto){
		
		System.out.println(dto.toString());
		return ResponseEntity.ok(BaseResponse.<CompanyCustomersInfoResponseDto>builder()
		                                     .message("Şirket bilgileri başarıyla güncellendi.")
		                                     .code(200)
		                                     .success(true)
		                                     .data(companyService.updateCompany(dto))
		                                     .build());
	}
}