package com.ajwalker.controller;

import com.ajwalker.dto.request.AddEmbezzlementRequestDto;
import com.ajwalker.dto.request.AssigmentEmbezzlementRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.EmbezzlementResponseDto;
import com.ajwalker.dto.response.GetEmbezzlementDetailsResponseDto;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.service.EmbezzlementService;
import com.ajwalker.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(EMBEZZLEMENT)
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmbezzlementController {
	private final EmbezzlementService embezzlementService;
	private final JwtManager jwtManager;
	
	@PostMapping(ADD_EMBEZZLEMENT)
	public ResponseEntity<BaseResponse<Boolean>> addEmbezzlement(@RequestBody @Valid AddEmbezzlementRequestDto dto){
		Optional<Long> managerId = jwtManager.verifyToken(dto.token());
		if(managerId.isEmpty()){
			throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
		}
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .message("Materyal şirketinize başarıyla eklendi.")
				                         .code(200)
				                         .success(true)
				                         .data(embezzlementService.addEmbezzlement(dto,managerId.get()))
				                         .build());
	}
	
	@GetMapping(GET_EMBEZZLEMENT_LIST)
	public ResponseEntity<BaseResponse<List<EmbezzlementResponseDto>>> embezzlementList(@RequestParam(name = "token") String token){
		Optional<Long> managerId = jwtManager.verifyToken(token);
		if(managerId.isEmpty()){
			throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
		}
		return ResponseEntity.ok(BaseResponse.<List<EmbezzlementResponseDto>>builder()
				                         .message("Zimmet listeniz getirildi")
				                         .code(200)
				                         .success(true)
				                         .data(embezzlementService.embezzlementList(managerId.get()))
		                                     .build());
	}
	
	@PutMapping(ASSIGMENT_EMBEZZLEMENT)
	public ResponseEntity<BaseResponse<Boolean>> assigmentEmbezzlement(@RequestBody @Valid AssigmentEmbezzlementRequestDto dto){
		Optional<Long> managerId = jwtManager.verifyToken(dto.token());
		if (managerId.isEmpty()){
			throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
		}
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .message("atama başarılı bir şekilde gercekleşti")
				                         .code(200)
				                         .success(true)
				                         .data(embezzlementService.assigmentEmbezzlement(dto,managerId.get()))
		                                     .build());
	}
	@GetMapping(GET_EMBEZZLEMENT_DETAILS)
	public ResponseEntity<BaseResponse<GetEmbezzlementDetailsResponseDto>> getEmbezzlementDetails(@RequestParam(name = "token") String token, @PathVariable Long embezzlementId){
		Optional<Long> managerId = jwtManager.verifyToken(token);
		if(managerId.isEmpty()){
			throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
		}
		return ResponseEntity.ok(BaseResponse.<GetEmbezzlementDetailsResponseDto>builder()
				                         .message("zimmet detay bilgisi getirildi")
				                         .code(200)
				                         .success(true)
				                         .data(embezzlementService.getEmbezzlementDetails(embezzlementId))
		                                     .build());
	}
	

}