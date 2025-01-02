package com.ajwalker.controller;

import com.ajwalker.dto.request.AddPersonalSpendingRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.EmployeesResponseDto;
import com.ajwalker.dto.response.PersonalSpendingResponseDto;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.service.PersonalSpendingService;
import com.ajwalker.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.ajwalker.constant.RestApis.*;


@RequiredArgsConstructor
@RequestMapping(PERSONAL_SPENDING)
@RestController
@CrossOrigin("*")
public class PersonalSpendingController {
	private final PersonalSpendingService personalSpendingService;
	private final JwtManager jwtManager;
	
	@PostMapping(ADD_PERSONAL_SPENDING)
	public ResponseEntity<BaseResponse<Boolean>> addPersonalSpending(@RequestBody @Valid AddPersonalSpendingRequestDto dto){
		Optional<Long> personalId = jwtManager.verifyToken(dto.token());
		if(personalId.isEmpty()){
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .message("Harcama başarıyla eklendi")
				                         .code(200)
				                         .success(true)
				                         .data(personalSpendingService.addPersonalSpending(dto,personalId.get()))
		                                     .build());
	}
	
	@GetMapping(GET_PERSONAL_SPENDINGS)
	public ResponseEntity<BaseResponse<List<PersonalSpendingResponseDto>>> getPersonalSpendingList(@RequestParam(name = "token") String token) {
		Optional<Long> optionalPersonalId = jwtManager.verifyToken(token);
		if (optionalPersonalId.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		return ResponseEntity.ok(BaseResponse.<List<PersonalSpendingResponseDto>>builder()
		                                     .message("Harcamalarınız başarıyla Listelendi")
		                                     .code(200)
		                                     .success(true)
		                                     .data(personalSpendingService.getPersonalSpendingList(optionalPersonalId.get()))
		                                     .build());
	}
	
}