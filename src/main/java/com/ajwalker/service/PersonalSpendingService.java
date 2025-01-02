package com.ajwalker.service;

import com.ajwalker.dto.request.AddPersonalSpendingRequestDto;
import com.ajwalker.entity.PersonalSpending;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.PersonalSpendingRepository;
import com.ajwalker.utility.Enum.personalSpending.ESpendingState;
import com.ajwalker.utility.Enum.personalSpending.ESpendingType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalSpendingService {
	private final PersonalSpendingRepository personalSpendingRepository;
    private final UserService userService;
	public Boolean addPersonalSpending(AddPersonalSpendingRequestDto dto) {
		Optional<User> optionalUser = userService.findById(dto.userId());
		if (optionalUser.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		User user = optionalUser.get();
		
		PersonalSpending personalSpending = PersonalSpending.builder()
				                                             .userId(user.getId())
				                                             .description(dto.description())
				                                             .billAmount(dto.billAmount())
				                                             .spendingDate(dto.spendingDate())
				                                             .spendingState(ESpendingState.PENDING)
				                                             .build();
		switch (dto.spendingType().toUpperCase()){
			case "ACCOMMODATION":
				personalSpending.setSpendingType(ESpendingType.ACCOMMODATION);
				break;
			case "MEAL":
				personalSpending.setSpendingType(ESpendingType.MEAL);
				break;
			case "TRANSACTION":
				personalSpending.setSpendingType(ESpendingType.TRANSACTION);
				break;
			case "OFFICE_SUPPLIES":
				personalSpending.setSpendingType(ESpendingType.OFFICE_SUPPLIES);
				break;
			default:
				personalSpending.setSpendingType(ESpendingType.NONE);
		}
		
		personalSpendingRepository.save(personalSpending);
		return true;
	}
}