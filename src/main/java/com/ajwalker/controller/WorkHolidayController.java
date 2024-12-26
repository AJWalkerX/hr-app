package com.ajwalker.controller;

import com.ajwalker.dto.request.WorkHolidayRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.service.UserAuthVerifyCodeService;
import com.ajwalker.service.WorkHolidayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(WORKHOLIDAY)
@RequiredArgsConstructor
@CrossOrigin("*")
public class WorkHolidayController {
	
	private final WorkHolidayService workHolidayService;
	private final UserAuthVerifyCodeService userAuthVerifyCodeService;
	
	

}