package com.ajwalker.service;

import com.ajwalker.dto.request.WorkHolidayRequestDto;
import com.ajwalker.dto.response.ViewYourUserPermitResponseDto;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.User;
import com.ajwalker.entity.WorkHoliday;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.WorkHolidayRepository;
import com.ajwalker.utility.Enum.holiday.EHolidayState;
import com.ajwalker.utility.Enum.holiday.EHolidayType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkHolidayService {
	
	private final WorkHolidayRepository workHolidayRepository;
	
	public Boolean createWorkHoliday(WorkHolidayRequestDto dto, Long userId) {
		

		WorkHoliday workHoliday = WorkHoliday.builder()
		                                     .userId(userId)
		                                     .beginDate(dto.beginDate())
		                                     .endDate(dto.endDate())
		                                     .holidayType(EHolidayType.valueOf(dto.holidayType())) // Enum'u string olarak aldığımız için dönüştürüyoruz.
		                                     .description(dto.description())
		                                     .holidayState(EHolidayState.PENDING) // İlk kayıt için varsayılan durumu belirtebilirsiniz.
		                                     .build();
		
		if (dto.beginDate().isAfter(dto.endDate()) || dto.beginDate().equals(dto.endDate())) {
			throw new IllegalArgumentException("Girmiş olduğunuz tarihler geçersizdir.");
		}
		
		workHolidayRepository.save(workHoliday);
		
		// Entity'den DTO'ya Dönüştürme ve Döndürme
		return true;
	
	}

	public void approveEmployeeHoliday(Long holidayId) {
		Optional<WorkHoliday> workHolidayOptional = workHolidayRepository.findById(holidayId);
		if (workHolidayOptional.isPresent()) {
			WorkHoliday workHoliday = workHolidayOptional.get();
			workHoliday.setHolidayState(EHolidayState.APPROVED);
			workHolidayRepository.save(workHoliday);
		}
	}

	public void rejectEmployeeHoliday(Long holidayId) {
		Optional<WorkHoliday> workHolidayOptional = workHolidayRepository.findById(holidayId);
		if (workHolidayOptional.isPresent()) {
			WorkHoliday workHoliday = workHolidayOptional.get();
			workHoliday.setHolidayState(EHolidayState.REJECTED);
			workHolidayRepository.save(workHoliday);
		}
	}

	public List<WorkHoliday> findAllWorkHolidaysInPending() {

		 return workHolidayRepository.findAllWorkHolidaysInPending(EHolidayState.PENDING);
	}
	
	public List<ViewYourUserPermitResponseDto> viewYourUserPermit(Long userId) {
	
		List<WorkHoliday> workHolidays = workHolidayRepository.findByUserId(userId);
		
		return workHolidays.stream().map(
				workHoliday -> new ViewYourUserPermitResponseDto(
						workHoliday.getUserId(),
						workHoliday.getId(),
						workHoliday.getBeginDate(),
						workHoliday.getEndDate(),
						workHoliday.getDescription(),
						workHoliday.getHolidayType().toString(),
						workHoliday.getHolidayState().toString()
				)
		).collect(Collectors.toList());
		
	
	}
}