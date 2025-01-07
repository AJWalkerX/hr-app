package com.ajwalker.controller;

import com.ajwalker.dto.request.AddEmbezzlementRequestDto;
import com.ajwalker.dto.request.AssigmentEmbezzlementRequestDto;
import com.ajwalker.dto.request.DeleteEmbezzlementRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.EmbezzlementResponseDto;
import com.ajwalker.dto.response.PersonalEmbezzlementResponseDto;
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
	
	@GetMapping(GET_ALL_MY_EMBEZZLEMENT_LIST)
	public ResponseEntity<BaseResponse<List<PersonalEmbezzlementResponseDto>>> getAllMyEmbezzlementList(@RequestParam(name = "token") String token){
		Optional<Long> personalId = jwtManager.verifyToken(token);
		if(personalId.isEmpty()){
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		return ResponseEntity.ok(BaseResponse.<List<PersonalEmbezzlementResponseDto>>builder()
				                         .message("Zimmet listeniz başarıyla getirildi")
				                         .success(true)
				                         .code(200)
				                         .data(embezzlementService.getAllMyEmbezzlementList(personalId.get()))
		                                     .build());
	}
	
	@PutMapping(DELETE_EMBEZZLEMENT_BY_USERID)
	public  ResponseEntity<BaseResponse<Boolean>> deleteEmbezzlementByUserId(@RequestBody @Valid DeleteEmbezzlementRequestDto dto){
		Optional<Long> managerId = jwtManager.verifyToken(dto.token());
		if(managerId.isEmpty()){
			throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
		}
		return  ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                          .message("personal ataması başarıyla kaldırıldı")
				                          .success(true)
				                          .code(200)
				                          .data(embezzlementService.deleteEmbezzlementByUserId(dto))
		                                      .build());
	}

}