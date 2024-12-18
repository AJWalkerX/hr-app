package com.ajwalker.utility.data;

import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.utility.Enum.user.EEmploymentStatus;
import com.ajwalker.utility.Enum.user.EGender;
import com.ajwalker.utility.Enum.user.EPosition;

import java.util.List;

public class PersonalDocumentGenerator {
	
	public static List<PersonalDocument> generatePersonalDocuments() {
		
		PersonalDocument personalDocument1 =
				PersonalDocument.builder().userId(1L).firstName("Ahmet").lastName("Eriş").identityNumber("10235489678")
				                .dateOfBirth(System.currentTimeMillis()).mobileNumber("05996218800").address("İzmir")
				                .gender(EGender.MALE).email("Ahmet35_array@gmail.com").position(EPosition.JUNIOR)
				                .dateOfEmployment(System.currentTimeMillis())
				                .dateOfTermination(System.currentTimeMillis()).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.FIRED).socialSecurityNumber("951753").build();
		PersonalDocument personalDocument2 =
				PersonalDocument.builder().userId(2L).firstName("Alper").lastName("Güler").identityNumber("10235489677")
				                .dateOfBirth(System.currentTimeMillis()).mobileNumber("05996218801").address("ANGARA")
				                .gender(EGender.MALE).email("Alper06_array@gmail.com").position(EPosition.MID_LEVEL)
				                .dateOfEmployment(System.currentTimeMillis())
				                .dateOfTermination(System.currentTimeMillis()).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.WORKING).socialSecurityNumber("951754").build();
		PersonalDocument personalDocument3 =
				PersonalDocument.builder().userId(3L).firstName("Alex").lastName("Walker").identityNumber("10235489679")
				                .dateOfBirth(System.currentTimeMillis()).mobileNumber("05996218802").address("ENGLAND")
				                .gender(EGender.MALE).email("Alex35_array@gmail.com").position(EPosition.INTERN)
				                .dateOfEmployment(System.currentTimeMillis())
				                .dateOfTermination(System.currentTimeMillis()).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.RESIGNATION).socialSecurityNumber("951755").build();
		PersonalDocument personalDocument4 =
				PersonalDocument.builder().userId(4L).firstName("Anıl").lastName("Özogli").identityNumber("10235489671")
				                .dateOfBirth(System.currentTimeMillis()).mobileNumber("05996218809").address("İstanbul")
				                .gender(EGender.MALE).email("Anıl34_array@gmail.com").position(EPosition.SENIOR)
				                .dateOfEmployment(System.currentTimeMillis())
				                .dateOfTermination(System.currentTimeMillis()).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.ON_HOLIDAY).socialSecurityNumber("951753").build();
		PersonalDocument personalDocument5 =
				PersonalDocument.builder().userId(5L).firstName("Emine").lastName("Karabolat").identityNumber(
						"10235489675")
				                .dateOfBirth(System.currentTimeMillis()).mobileNumber("05996218800").address("İzmir")
				                .gender(EGender.FEMALE).email("emine01_array@gmail.com").position(EPosition.MANAGER)
				                .dateOfEmployment(System.currentTimeMillis())
				                .dateOfTermination(System.currentTimeMillis()).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.WORKING).socialSecurityNumber("951753").build();
		
		return List.of(personalDocument1, personalDocument2, personalDocument3, personalDocument4, personalDocument5);
	}
}