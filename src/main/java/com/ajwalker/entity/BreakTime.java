package com.ajwalker.entity;

import com.ajwalker.utility.Enum.EBrakeTimeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_break_time")
public class BreakTime extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Long shiftTraking;
	private Long beginHour;
	private Long endHour;
	private Long date;
	private EBrakeTimeType brakeTimeType;
	
}