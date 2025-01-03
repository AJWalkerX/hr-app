package com.ajwalker.service;

import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.PersonalSpending;
import com.ajwalker.entity.Salary;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalaryService {
	private final SalaryRepository salaryRepository;
	private final PersonalDocumentService personalDocumentService;
	
	public void generateMonthlySalaries(Long userId) {
		// PersonalDocument'ı userId ile veritabanından çek
		Optional<PersonalDocument> optionalPersonalDocument = personalDocumentService.findByUserId(userId);
		
		if (optionalPersonalDocument.isPresent()) {
			PersonalDocument personalDocument = optionalPersonalDocument.get();
			Double annualSalary = Double.valueOf(personalDocument.getAnnualSalary());
			Double monthlySalary = annualSalary / 12.0;
			
			LocalDate currentDate = LocalDate.now();
			
			for (int i = 1; i <= 12; i++) {
				Salary salary = Salary.builder()
				                      .userId(personalDocument.getUserId()) // Bağlantıyı userId ile kur
				                      .monthlySalary(monthlySalary)
				                      .salaryDate(currentDate.withDayOfMonth(1)) // Maaş tarihini ayarla
				                      .build();
				
				currentDate = currentDate.plusMonths(1); // Bir sonraki aya geç
				salaryRepository.save(salary); // Maaşı veritabanına kaydet
			}
		} else {
			throw new RuntimeException("Kullanıcı bulunamadı: " + userId);
		}
	}
	

	public void addSpendingToSalary(Long userId, PersonalSpending personalSpending) {
		LocalDate nextMonth = personalSpending.getSpendingDate().plusMonths(1).withDayOfMonth(1);
		Optional<Salary> optionalSalary = salaryRepository.findByUserIdAndSalaryDate(userId, nextMonth);
		if (optionalSalary.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_SALARY);
		}
		Salary salary = optionalSalary.get();
		salary.setBonusAmount(personalSpending.getBillAmount());
		salaryRepository.save(salary);
	}
}