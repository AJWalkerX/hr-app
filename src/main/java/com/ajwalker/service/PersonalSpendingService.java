package com.ajwalker.service;

import com.ajwalker.dto.request.AddPersonalSpendingRequestDto;
import com.ajwalker.dto.response.PersonalSpendingResponseDto;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalSpendingService {
	private final PersonalSpendingRepository personalSpendingRepository;
	private final UserService userService;
	
	public Boolean addPersonalSpending(AddPersonalSpendingRequestDto dto, Long personalId) {
		Optional<User> optionalUser = userService.findById(personalId);
		if (optionalUser.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		User user = optionalUser.get();
		
		PersonalSpending personalSpending = PersonalSpending.builder().userId(personalId).description(dto.description())
		                                                    .billAmount(dto.billAmount())
		                                                    .spendingDate(dto.spendingDate())
		                                                    .spendingState(ESpendingState.PENDING).build();
		switch (dto.spendingType().toUpperCase()) {
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
	
	public List<PersonalSpendingResponseDto> getPersonalSpendingList(Long personalId) {
		Optional<User> optUser = userService.findById(personalId);
		if (optUser.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		User user = optUser.get();
		List<PersonalSpending> personalSpendingList = personalSpendingRepository.findAllByUserId(personalId);
		
		return personalSpendingList.stream().map(personalSpending -> new PersonalSpendingResponseDto(
				personalSpending.getSpendingDate(),
				personalSpending.getDescription(),
				personalSpending.getBillAmount(),
				personalSpending.getSpendingType().toString(),
				personalSpending.getSpendingState().toString())
		).collect(Collectors.toList());
	}
	
}