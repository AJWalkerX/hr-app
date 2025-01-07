package com.ajwalker.utility.data;

import com.ajwalker.entity.Shift;

import java.time.LocalTime;
import java.util.List;

public class ShiftGenerator {

	public static List<Shift> generateShiftList(){
		Shift shift1 = Shift.builder()
				.companyId(3L)
				.beginHour(LocalTime.of(8,0))
				.endHour(LocalTime.of(16,59))
				            .shiftName("Sabah Vardiyası")
		                    .build();
		
		Shift shift2 = Shift.builder()
		                    .companyId(3L)
		                    .beginHour(LocalTime.of(17,00))
		                    .endHour(LocalTime.of(0,0))
		                    .shiftName("Akşam Vardiyası")
		                    .build();
		
		Shift shift3 = Shift.builder()
		                    .companyId(3L)
		                    .beginHour(LocalTime.of(0,0))
		                    .endHour(LocalTime.of(7,59))
		                    .shiftName("Gece Vardiyası")
		                    .build();
		
		
		Shift shift4 = Shift.builder()
		                    .companyId(5L)
		                    .beginHour(LocalTime.of(8,0))
		                    .endHour(LocalTime.of(16,59))
		                    .shiftName("Sabah Vardiyası")
		                    .build();
		
		Shift shift5 = Shift.builder()
		                    .companyId(5L)
		                    .beginHour(LocalTime.of(17,00))
		                    .endHour(LocalTime.of(0,0))
		                    .shiftName("Akşam Vardiyası")
		                    .build();
		
		Shift shift6 = Shift.builder()
		                    .companyId(5L)
		                    .beginHour(LocalTime.of(0,0))
		                    .endHour(LocalTime.of(7,59))
		                    .shiftName("Gece Vardiyası")
		                    .build();
		return List.of(shift1, shift2, shift3, shift4, shift5, shift6);
	}
}