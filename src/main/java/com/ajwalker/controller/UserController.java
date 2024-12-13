package com.ajwalker.controller;

import com.ajwalker.constant.ReactApis;
import com.ajwalker.dto.request.DologinRequestDto;
import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.service.UserAuthVerifyCodeService;
import com.ajwalker.service.UserService;
import com.ajwalker.utility.Enum.user.EUserState;
import com.ajwalker.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
@CrossOrigin("*")  //Frontend isteklerini sınırlar, * koyunca her porttan gelen istekleri kabul eder
public class UserController {
	private final UserService userService;
	private final UserAuthVerifyCodeService userAuthVerifyCodeService;

	@PostMapping(REGISTER)
	public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid RegisterRequestDto dto) {
		if (!dto.password().equals((dto.rePassword()))) throw new HRAppException(ErrorType.PASSWORD_ERROR);

		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .code(200)
		                                     .data(userService.register(dto))
		                                     .message("Uyelik islemini tamamlamak icin mail onayinizi bekliyoruz!")
		                                     .success(true)
				.build());
	}

	@GetMapping(AUTHMAIL)
	public ResponseEntity<Void> authUser(@RequestParam(name = "auth") String authCode){
		Optional<Long> userIdOptional = userAuthVerifyCodeService.findUserIdByAuthCode(authCode);
		if (userIdOptional.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}

		userService.updateUserStateToInReView(userIdOptional.get());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(ReactApis.DOMAIN));
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}

	@PostMapping(DOLOGIN)
	public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid DologinRequestDto dto){
		String token = userService.doLogin(dto);

		return ResponseEntity.ok(BaseResponse.<String>builder()
				                         .success(true)
				                         .message("Giriş işlemi başarılı")
				                         .data(token)
				                         .code(200)
		                                     .build()
		);
	}
}