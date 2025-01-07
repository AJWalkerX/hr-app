package com.ajwalker.service;

import com.ajwalker.dto.request.AddEmbezzlementRequestDto;
import com.ajwalker.dto.request.AssigmentEmbezzlementRequestDto;
import com.ajwalker.dto.response.EmbezzlementResponseDto;
import com.ajwalker.dto.response.PersonalEmbezzlementResponseDto;
import com.ajwalker.entity.Embezzlement;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.EmbezzlementRepository;
import com.ajwalker.utility.Enum.embezzlement.EEmbezzlementState;
import com.ajwalker.utility.Enum.embezzlement.EEmbezzlementType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmbezzlementService {
	private final EmbezzlementRepository embezzlementRepository;
	private final UserService userService;
	private final PersonalDocumentService personalDocumentService;
	
	public Boolean addEmbezzlement(AddEmbezzlementRequestDto dto, Long managerId) {
		
		
		User user = userService.findById(managerId).orElseThrow(() -> new HRAppException(ErrorType.NOTFOUND_USER));
		
		if (user.getCompanyId() == null) {
			throw new HRAppException(ErrorType.NOTFOUND_COMPANY);
		}
		
		Embezzlement embezzlement = new Embezzlement();
		embezzlement.setTitle(dto.title());
		embezzlement.setDescription(dto.description());
		embezzlement.setCompanyId(user.getCompanyId());
		embezzlement.setEmbezzlementState(EEmbezzlementState.PENDING);
		embezzlement.setManagerId(user.getId());
		
		switch(dto.embezzlementType().toUpperCase()){
			case "ELECTRONICDEVICES":
				embezzlement.setEmbezzlementType(EEmbezzlementType.ELECTRONICDEVICES);
				break;
			case "OFFICEEQUIPMENT":
				embezzlement.setEmbezzlementType(EEmbezzlementType.OFFICEEQUIPMENT);
				break;
			case "VEHICLESANDTRANSPORTATION":
				embezzlement.setEmbezzlementType(EEmbezzlementType.VEHICLESANDTRANSPORTATION);
				break;
			case "PROTECTIVEGEAR":
				embezzlement.setEmbezzlementType(EEmbezzlementType.PROTECTIVEGEAR);
				break;
			case "TECHNICALEQUIPMENT":
				embezzlement.setEmbezzlementType(EEmbezzlementType.TECHNICALEQUIPMENT);
				break;
			case "STATIONERYANDOFFICESUPPLIES":
				embezzlement.setEmbezzlementType(EEmbezzlementType.STATIONERYANDOFFICESUPPLIES);
				break;
			case "COMPANYCARDS":
				embezzlement.setEmbezzlementType(EEmbezzlementType.COMPANYCARDS);
				break;
			case "CLOTHINGANDUNIFORMS":
				embezzlement.setEmbezzlementType(EEmbezzlementType.CLOTHINGANDUNIFORMS);
				break;
		}
		
		embezzlementRepository.save(embezzlement);
		
		return true;
	}
	
	
	public Boolean assigmentEmbezzlement(AssigmentEmbezzlementRequestDto dto, Long managerId) {
		
		PersonalDocument personalDocument = personalDocumentService.findByFirstNameAndLastNameAndEmail(dto.firstName(), dto.lastName(), dto.email());
		if (personalDocument == null) {
			throw new HRAppException(ErrorType.NOTFOUND_PERSONALDOCUMENT);
			
		}
		
		
		User user = userService.findById(personalDocument.getUserId()).orElse(null);
		if (user == null) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
			
		}
		
		
		User manager = userService.findById(managerId).orElse(null);
		if (manager == null || !manager.getCompanyId().equals(user.getCompanyId())) {
			throw new HRAppException(ErrorType.MANAGER_AND_PERSONAL_NOT_SAME_COMPANY);
			
		}
		
		
		Embezzlement embezzlement = embezzlementRepository.findById(dto.embezzlementId()).orElse(null);
		if (embezzlement == null) {
			throw new HRAppException(ErrorType.NOT_FOUND_EMBEZZLEMENT);
			
		}
		
		
		embezzlement.setUserId(user.getId());
		embezzlement.setEmbezzlementState(EEmbezzlementState.ASSIGNED);
		embezzlementRepository.save(embezzlement);
		
		return true;
	}
	
	
	
	
	public List<EmbezzlementResponseDto> embezzlementList(Long managerId) {
		
		User manager = userService.findUserById(managerId)
		                          .orElseThrow(() -> new HRAppException(ErrorType.NOTFOUND_USER));
		
		Long companyId = manager.getCompanyId();
		List<Embezzlement> embezzlementList = embezzlementRepository.findEmbezzlementByCompanyId(companyId);
		
		return embezzlementList.stream()
		                       .map(embezzlement -> {
			                      
			                       if (embezzlement.getUserId() == null) {
				                       return new EmbezzlementResponseDto(
						                       embezzlement.getId(),
						                       embezzlement.getCompanyId(),
						                       null,
						                       embezzlement.getDescription(),
						                       embezzlement.getEmbezzlementType().toString(),
						                       embezzlement.getEmbezzlementState().toString(),
											   embezzlement.getTitle(),
						                       null
				                       );
			                       }
			                       
			                       
			                       Long userId = embezzlement.getUserId();
			                       User user = userService.findUserById(userId)
			                                              .orElseThrow(() -> new HRAppException(ErrorType.NOTFOUND_USER));
								   
			                       PersonalDocument personalDocument = personalDocumentService.findByUserId(userId)
			                                                                                  .orElseThrow(() -> new EntityNotFoundException("PersonalDocument not found for user id: " + userId));
								   
			                       return new EmbezzlementResponseDto(
					                       embezzlement.getId(),
					                       embezzlement.getCompanyId(),
					                       embezzlement.getUserId(),
					                       embezzlement.getDescription(),
					                       embezzlement.getEmbezzlementType().toString(),
					                       embezzlement.getEmbezzlementState().toString(),
										   embezzlement.getTitle(),
					                       new EmbezzlementResponseDto.UserDetails(
							                       user.getAvatar(),
							                       personalDocument.getFirstName(),
							                       personalDocument.getLastName()
					                       )
			                       );
		                       })
		                       .toList();
	}
	
	
	public List<PersonalEmbezzlementResponseDto> getAllMyEmbezzlementList(Long personalId) {
		Optional<User> optUser = userService.findById(personalId);
		if (optUser.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		User user = optUser.get();
		List<Embezzlement> myEmbezzlementList = embezzlementRepository.findAllByUserId(personalId);
		
		return myEmbezzlementList.stream().map(embezzlement -> new PersonalEmbezzlementResponseDto(
				embezzlement.getTitle(),
				embezzlement.getDescription(),
				Instant.ofEpochMilli(embezzlement.getUpdate_at()) // long değeri Instant'a çevir
				       .atZone(ZoneId.systemDefault()) // Sistem saat dilimini kullan
				       .toLocalDate() // LocalDate'e dönüştür
		)).collect(Collectors.toList());
		
	}
}