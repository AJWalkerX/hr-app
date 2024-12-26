package com.ajwalker.entity;

import com.ajwalker.utility.Enum.holiday.EHolidayState;
import com.ajwalker.utility.Enum.holiday.EHolidayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_work_holiday")
public class WorkHoliday extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private LocalDate beginDate;
	private LocalDate endDate;
	@Enumerated(EnumType.STRING)
	private EHolidayState holidayState;
	@Enumerated(EnumType.STRING)
	private EHolidayType holidayType;
	private String description;

	
}