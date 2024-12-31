package com.ajwalker.controller;

import com.ajwalker.entity.Salary;
import com.ajwalker.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {
	
	@Autowired
	private SalaryService salaryService;
	
	@PostMapping("/generate/{userId}")
	public ResponseEntity<String> generateSalaries(@PathVariable Long userId) {
		try {
			salaryService.generateMonthlySalaries(userId);
			return ResponseEntity.ok("Maaşlar başarıyla oluşturuldu.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			                     .body("Hata oluştu: " + e.getMessage());
		}
	}
	
	@GetMapping("/list/{userId}")
	public ResponseEntity<List<Salary>> getSalaries(@PathVariable Long userId) {
		List<Salary> salaries = salaryService.findByUserId(userId);
		if (salaries.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(salaries);
	}
}