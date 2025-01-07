package com.ajwalker.utility.data;

import com.ajwalker.entity.Embezzlement;
import com.ajwalker.utility.Enum.embezzlement.EEmbezzlementState;
import com.ajwalker.utility.Enum.embezzlement.EEmbezzlementType;

import java.util.List;

public class EmbezzlementGenerator {
	
	public static List<Embezzlement> generateEmbezzlementList() {
		Embezzlement embezzlement1 = Embezzlement.builder()
				.managerId(5L)
				.companyId(5L)
				.userId(7L)
				.title("Laptop")
				.description("Asus Dizüstü Bilgisayar")
				.embezzlementType(EEmbezzlementType.ELECTRONICDEVICES)
				.embezzlementState(EEmbezzlementState.ASSIGNED)
		                                         .build();
		
		Embezzlement embezzlement2 = Embezzlement.builder()
		                                         .managerId(5L)
		                                         .companyId(5L)
		                                         .userId(7L)
		                                         .title("Laptop")
		                                         .description("Monster Dizüstü Bilgisayar")
		                                         .embezzlementType(EEmbezzlementType.ELECTRONICDEVICES)
		                                         .embezzlementState(EEmbezzlementState.ASSIGNED)
		                                         .build();
		
		Embezzlement embezzlement3 = Embezzlement.builder()
		                                         .managerId(5L)
		                                         .companyId(5L)
		                                         
		                                         .title("Giriş kartı")
		                                         .description("Personel Giriş kartı")
		                                         .embezzlementType(EEmbezzlementType.COMPANYCARDS)
		                                         .embezzlementState(EEmbezzlementState.PENDING)
		                                         .build();
		
		Embezzlement embezzlement4 = Embezzlement.builder()
		                                         .managerId(8L)
		                                         .companyId(3L)
		                                         .userId(4L)
		                                         .title("Araba")
		                                         .description("Transporter Minibüs")
		                                         .embezzlementType(EEmbezzlementType.VEHICLESANDTRANSPORTATION)
		                                         .embezzlementState(EEmbezzlementState.ASSIGNED)
		                                         .build();
		
		Embezzlement embezzlement5 = Embezzlement.builder()
		                                         .managerId(8L)
		                                         .companyId(3L)
		                                         .userId(4L)
		                                         .title("Sandalye")
		                                         .description("Ofis Sandalyesi")
		                                         .embezzlementType(EEmbezzlementType.OFFICEEQUIPMENT)
		                                         .embezzlementState(EEmbezzlementState.ASSIGNED)
		                                         .build();
		Embezzlement embezzlement6 = Embezzlement.builder()
		                                         .managerId(8L)
		                                         .companyId(3L)
		                                         .title("Laptop")
		                                         .description("Asus Dizüstü Bilgisayar")
		                                         .embezzlementType(EEmbezzlementType.ELECTRONICDEVICES)
		                                         .embezzlementState(EEmbezzlementState.PENDING)
		                                         .build();
		
		return List.of(embezzlement1,embezzlement2,embezzlement3,embezzlement4,embezzlement5,embezzlement6);
	}
	
}