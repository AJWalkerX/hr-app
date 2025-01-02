package com.ajwalker.controller;
import com.ajwalker.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import static com.ajwalker.constant.RestApis.*;


@RequiredArgsConstructor
@RestController
@RequestMapping(SALARY)
public class SalaryController {
	private SalaryService salaryService;
	
	
	
}