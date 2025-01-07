package com.ajwalker.service;

import com.ajwalker.dto.request.AddEmbezzlementRequestDto;
import com.ajwalker.dto.request.AssigmentEmbezzlementRequestDto;
import com.ajwalker.dto.response.EmbezzlementResponseDto;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmbezzlementService {
	private final EmbezzlementRepository embezzlementRepository;
	private final UserService userService;
	private final PersonalDocumentService personalDocumentService;
	
	public Boolean addEmbezzlement(AddEmbezzlementRequestDto dto, Long managerId) {
		User user = userService.findById(managerId).orElseThrow(() -> new HRAppException(ErrorType.NOTFOUND_USER));
		
		// Kullanıcının bir şirkete bağlı olup olmadığını kontrol et
		if (user.getCompanyId() == null) {
			throw new HRAppException(ErrorType.NOTFOUND_COMPANY);
		}
		
		// Yeni bir zimmet kaydı oluştur
		Embezzlement embezzlement = new Embezzlement();
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
		
		// Zimmet kaydını veritabanına kaydet
		embezzlementRepository.save(embezzlement);
		
		return true;
	}
	
	
	
	
	
	/**
	 * ad soyad ve email ile kullanıcı varmı kontrol et
	 * frontend de tıklanan embezlementın id si bulunacak
	 * manager companyid ile bulunan personalın companyid si aynımı kontrol edilecek
	 * eğer butun veriler dogruysa embezzlementın user id si güncellenecek
	 *
	 */
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
			                       // Eğer userId null ise
			                       if (embezzlement.getUserId() == null) {
				                       return new EmbezzlementResponseDto(
						                       embezzlement.getId(),
						                       embezzlement.getCompanyId(),
						                       null,
						                       embezzlement.getDescription(),
						                       embezzlement.getEmbezzlementType().toString(),
						                       embezzlement.getEmbezzlementState().toString(),
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
					                       new EmbezzlementResponseDto.UserDetails(
							                       user.getAvatar(),
							                       personalDocument.getFirstName(),
							                       personalDocument.getLastName()
					                       )
			                       );
		                       })
		                       .toList();
	}
	
	
}