package com.ajwalker.service;

import com.ajwalker.dto.request.AddEmbezzlementRequestDto;
import com.ajwalker.dto.response.EmbezzlementResponseDto;
import com.ajwalker.entity.Embezzlement;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.EmbezzlementRepository;
import com.ajwalker.utility.Enum.embezzlement.EEmbezzlementState;
import com.ajwalker.utility.Enum.embezzlement.EEmbezzlementType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	public List<EmbezzlementResponseDto> embezzlementList(Long managerId) {
		// Manager'ın varlığını kontrol et
		User user = userService.findById(managerId)
		                       .orElseThrow(() -> new HRAppException(ErrorType.NOTFOUND_USER));
		
		// Şirket ID'sini al
		Long companyId = user.getCompanyId();
		
		// Şirkete ait embezzlement kayıtlarını al
		List<Embezzlement> embezzlementList = embezzlementRepository.findEmbezzlementByCompanyId(companyId);
		
		// Kişisel Belgeyi al, null kontrolü ekleniyor
		PersonalDocument personalDocument = personalDocumentService.findPersonalByUserId(user.getId());
		
		
		// Embezzlement'ları DTO'ya dönüştür ve döndür
		return embezzlementList.stream()
		                       .map(embezzlement -> {
			                       // Zimmetin kullanıcı bilgilerini DTO'ya eklerken, null kontrolleri yapılır.
			                       String firstName = personalDocument.getFirstName() != null ? personalDocument.getFirstName() : "N/A";
			                       String lastName = personalDocument.getLastName() != null ? personalDocument.getLastName() : "N/A";
			                       String avatar = user.getAvatar() != null ? user.getAvatar() : "default-avatar.png";
			                       return new EmbezzlementResponseDto(
					                       embezzlement.getUserId(),
					                       companyId,
					                       embezzlement.getDescription(),
					                       embezzlement.getEmbezzlementType().toString(),
					                       embezzlement.getEmbezzlementState().toString(),
					                       avatar,
					                       firstName,
					                       lastName
			                       );
		                       })
		                       .collect(Collectors.toList());
	}
	
}