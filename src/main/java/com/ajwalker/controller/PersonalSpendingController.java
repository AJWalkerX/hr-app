package com.ajwalker.controller;

import com.ajwalker.dto.request.AddPersonalSpendingRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.service.PersonalSpendingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ajwalker.constant.RestApis.*;


@RequiredArgsConstructor
@RequestMapping(PERSONAL_SPENDING)
@RestController
@CrossOrigin("*")
public class PersonalSpendingController {
	private final PersonalSpendingService personalSpendingService;
	
	@PostMapping(ADD_PERSONAL_SPENDING)
	public ResponseEntity<BaseResponse<Boolean>> addPersonalSpending(@RequestBody @Valid AddPersonalSpendingRequestDto dto){
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .message("Harcama başarıyla eklendi")
				                         .code(200)
				                         .success(true)
				                         .data(personalSpendingService.addPersonalSpending(dto))
		                                     .build());
	}
	
}