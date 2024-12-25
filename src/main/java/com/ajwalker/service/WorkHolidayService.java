package com.ajwalker.service;

import com.ajwalker.dto.request.WorkHolidayDto;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.WorkHoliday;
import com.ajwalker.repository.PersonalDocumentRepository;
import com.ajwalker.repository.WorkHolidayRepository;
import com.ajwalker.utility.Enum.holiday.EHolidayState;
import com.ajwalker.utility.Enum.holiday.EHolidayType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class WorkHolidayService {
	
	private final WorkHolidayRepository workHolidayRepository;
	private  final PersonalDocumentService personalDocumentService;
	
	public WorkHoliday findById(Long id) {
		return workHolidayRepository.findById(id).orElse(null);
	}
	
	public WorkHolidayDto createWorkHoliday(WorkHolidayDto dto) {
		
		PersonalDocument personalDocument = personalDocumentService.personalFindById(dto.userId());
		
		// Tarihleri dönüştürme
		LocalDate startDate = Instant.ofEpochMilli(dto.beginDate()).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate endDate = Instant.ofEpochMilli(dto.endDate()).atZone(ZoneId.systemDefault()).toLocalDate();
		
		
		
		WorkHoliday workHoliday = WorkHoliday.builder()
		                                     .userId(dto.userId())
		                                     .beginDate(dto.beginDate()) // Tarih için dönüştürme gerekebilir.
		                                     .endDate(dto.endDate())     // Aynı şekilde burada da.
		                                     .holidayType(EHolidayType.valueOf(dto.holidayType())) // Enum'u string olarak aldığımız için dönüştürüyoruz.
		                                     .description(dto.description())
		                                     .holidayState(EHolidayState.PENDING) // İlk kayıt için varsayılan durumu belirtebilirsiniz.
		                                     .build();
		
		// Doğrulama
		if (startDate.isAfter(endDate)) {
			throw new IllegalArgumentException("Start date cannot be after end date.");
		}
		
		WorkHoliday savedWorkHoliday = workHolidayRepository.save(workHoliday);
		
		// Entity'den DTO'ya Dönüştürme ve Döndürme
		return new WorkHolidayDto(
				savedWorkHoliday.getUserId(),
				personalDocument.getFirstName(),
				personalDocument.getLastName(),
				personalDocument.getMobileNumber(),
				personalDocument.getEmail(),
				savedWorkHoliday.getBeginDate(),
				savedWorkHoliday.getEndDate(),
				savedWorkHoliday.getHolidayType().toString(),
				savedWorkHoliday.getDescription()
		);
	
	}
}