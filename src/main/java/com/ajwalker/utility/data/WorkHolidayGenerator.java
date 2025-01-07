package com.ajwalker.utility.data;

import com.ajwalker.entity.WorkHoliday;
import com.ajwalker.utility.Enum.holiday.EHolidayState;
import com.ajwalker.utility.Enum.holiday.EHolidayType;

import java.time.LocalDate;
import java.util.List;

public class WorkHolidayGenerator {
	
	public static List<WorkHoliday> generateWorkHolidayList() {
		WorkHoliday workHoliday1 = WorkHoliday.builder()
				.userId(5L)
				.beginDate(LocalDate.of(2025,2,15))
				.endDate(LocalDate.of(2025,2,22))
				.description("Yıllık İzin hakkımdan kullanmak istiyorum")
				.holidayState(EHolidayState.PENDING)
				.holidayType(EHolidayType.ANNUAL_LEAVE)
		                                      .build();
		
		WorkHoliday workHoliday2 = WorkHoliday.builder()
		                                      .userId(4L)
		                                      .beginDate(LocalDate.of(2025,2,15))
		                                      .endDate(LocalDate.of(2025,2,22))
		                                      .description("Hastaneye gideceğim")
		                                      .holidayState(EHolidayState.PENDING)
		                                      .holidayType(EHolidayType.SICK_LEAVE)
		                                      .build();
		
		WorkHoliday workHoliday3 = WorkHoliday.builder()
		                                      .userId(7L)
		                                      .beginDate(LocalDate.of(2025,2,15))
		                                      .endDate(LocalDate.of(2025,2,22))
		                                      .description("Yıllık İzin hakkımdan kullanmak istiyorum")
		                                      .holidayState(EHolidayState.PENDING)
		                                      .holidayType(EHolidayType.ANNUAL_LEAVE)
		                                      .build();
		
		WorkHoliday workHoliday4 = WorkHoliday.builder()
		                                      .userId(8L)
		                                      .beginDate(LocalDate.of(2025,3,8))
		                                      .endDate(LocalDate.of(2025,2,22))
		                                      .description("Yıllık İzin hakkımdan kullanıyorum")
		                                      .holidayState(EHolidayState.PENDING)
		                                      .holidayType(EHolidayType.ANNUAL_LEAVE)
		                                      .build();
		return List.of(workHoliday1, workHoliday2, workHoliday3, workHoliday4);
	}
}