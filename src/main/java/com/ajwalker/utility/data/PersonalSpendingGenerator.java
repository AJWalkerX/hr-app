package com.ajwalker.utility.data;

import com.ajwalker.entity.PersonalSpending;
import com.ajwalker.utility.Enum.personalSpending.ESpendingState;
import com.ajwalker.utility.Enum.personalSpending.ESpendingType;

import java.time.LocalDate;
import java.util.List;

public class PersonalSpendingGenerator {
	public static List<PersonalSpending> generatePersonalSpendingList(){
		PersonalSpending personalSpending1 = PersonalSpending.builder()
				.userId(7L)
				.billAmount(500D)
				.spendingState(ESpendingState.PENDING)
				.spendingType(ESpendingType.MEAL)
				.description("Öğle Yemeği")
				.spendingDate(LocalDate.of(2024, 12, 8))
		                                                     .build();
		
		PersonalSpending personalSpending2 = PersonalSpending.builder()
		                                                     .userId(7L)
		                                                     .billAmount(800D)
		                                                     .spendingState(ESpendingState.PENDING)
		                                                     .spendingType(ESpendingType.TRANSACTION)
		                                                     .description("Ulaşım Ücreti")
		                                                     .spendingDate(LocalDate.of(2024, 12, 4))
		                                                     .build();
		
		PersonalSpending personalSpending3 = PersonalSpending.builder()
		                                                     .userId(4L)
		                                                     .billAmount(5000D)
		                                                     .spendingState(ESpendingState.PENDING)
		                                                     .spendingType(ESpendingType.ACCOMMODATION)
		                                                     .description("Konaklama Ücreti")
		                                                     .spendingDate(LocalDate.of(2024, 11, 8))
		                                                     .build();
		
		PersonalSpending personalSpending4 = PersonalSpending.builder()
		                                                     .userId(4L)
		                                                     .billAmount(250D)
		                                                     .spendingState(ESpendingState.PENDING)
		                                                     .spendingType(ESpendingType.MEAL)
		                                                     .description("Akşam Yemeği")
		                                                     .spendingDate(LocalDate.of(2024, 12, 1))
		                                                     .build();
		
		return List.of(personalSpending1, personalSpending2, personalSpending3, personalSpending4);
	}
}