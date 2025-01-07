package com.ajwalker.utility.data;

import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.utility.Enum.user.EEmploymentStatus;
import com.ajwalker.utility.Enum.user.EGender;
import com.ajwalker.utility.Enum.user.EPosition;

import java.time.LocalDate;
import java.util.List;

public class PersonalDocumentGenerator {
	
	public static List<PersonalDocument> generatePersonalDocuments() {
		
		PersonalDocument personalDocument1 =
				PersonalDocument.builder().userId(1L).firstName("Ahmet").lastName("Eriş").identityNumber("10235489678")
				                .dateOfBirth(LocalDate.of(2000,8,13)).mobileNumber("05996218800").address("İzmir")
				                .gender(EGender.MALE).email("kullanici1@gmail.com").position(EPosition.JUNIOR)
				                .dateOfEmployment(LocalDate.of(2000,8,13))
				                .dateOfTermination(LocalDate.of(2000,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.FIRED).socialSecurityNumber("951753").build();
		PersonalDocument personalDocument2 =
				PersonalDocument.builder().userId(2L).firstName("Alper").lastName("Güler").identityNumber("10235489677")
				                .dateOfBirth(LocalDate.of(2000,8,13)).mobileNumber("05996218801").address("ANGARA")
				                .gender(EGender.MALE).email("kullanici2@gmail.com").position(EPosition.MID_LEVEL)
				                .dateOfEmployment(LocalDate.of(2000,8,13))
				                .dateOfTermination(LocalDate.of(2000,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.WORKING).socialSecurityNumber("951754").build();
		PersonalDocument personalDocument3 =
				PersonalDocument.builder().userId(3L).firstName("Alex").lastName("Walker").identityNumber("10235489679")
				                .dateOfBirth(LocalDate.of(2000,8,13)).mobileNumber("05996218802").address("ENGLAND")
				                .gender(EGender.MALE).email("kullanici3@gmail.com").position(EPosition.INTERN)
				                .dateOfEmployment(LocalDate.of(2000,8,13))
				                .dateOfTermination(LocalDate.of(2000,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.RESIGNATION).socialSecurityNumber("951755").build();
		PersonalDocument personalDocument4 =
				PersonalDocument.builder().userId(4L).firstName("Anıl").lastName("Özoğluöz").identityNumber("10235489671")
				                .dateOfBirth(LocalDate.of(2000,8,13)).mobileNumber("05996218809").address("İstanbul")
				                .gender(EGender.MALE).email("kullanici4@gmail.com").position(EPosition.SENIOR)
				                .dateOfEmployment(LocalDate.of(2000,8,13))
				                .dateOfTermination(LocalDate.of(2000,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.ON_HOLIDAY).socialSecurityNumber("951753").build();
		PersonalDocument personalDocument5 =
				PersonalDocument.builder().userId(5L).firstName("Emine").lastName("Karabolat").identityNumber(
						"10235489675")
				                .dateOfBirth(LocalDate.of(2000,8,13)).mobileNumber("05996218800").address("İzmir")
				                .gender(EGender.FEMALE).email("kullanici5@gmail.com").position(EPosition.MANAGER)
				                .dateOfEmployment(LocalDate.of(2000,8,13))
				                .dateOfTermination(LocalDate.of(2000,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.WORKING).socialSecurityNumber("951753").build();
		PersonalDocument personalDocument6 =
				PersonalDocument.builder().userId(6L).firstName("Ayşe").lastName("Kaya").identityNumber(
						                "10235489675")
				                .dateOfBirth(LocalDate.of(2000,8,13)).mobileNumber("05996218800").address("İzmir")
				                .gender(EGender.FEMALE).email("kullanici6@gmail.com").position(EPosition.INTERN)
				                .dateOfEmployment(LocalDate.of(2000,8,13))
				                .dateOfTermination(LocalDate.of(2000,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.WORKING).socialSecurityNumber("9517123").build();
		PersonalDocument personalDocument7 =
				PersonalDocument.builder().userId(7L).firstName("Defne").lastName("Yılmaz").identityNumber(
						                "10235489675")
				                .dateOfBirth(LocalDate.of(2000,8,13)).mobileNumber("05996218800").address("İzmir")
				                .gender(EGender.FEMALE).email("kullanici7@gmail.com").position(EPosition.MID_LEVEL)
				                .dateOfEmployment(LocalDate.of(2000,8,13))
				                .dateOfTermination(LocalDate.of(2000,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.WORKING).socialSecurityNumber("9517124").build();
		PersonalDocument personalDocument8 =
				PersonalDocument.builder().userId(8L).firstName("Mehmet").lastName("Ertop").identityNumber(
						                "10235489675")
				                .dateOfBirth(LocalDate.of(1999,03,10)).mobileNumber("059545646546").address("Ankara")
				                .gender(EGender.MALE).email("kullanici8@gmail.com").position(EPosition.MANAGER)
				                .dateOfEmployment(LocalDate.of(2000,8,13))
				                .dateOfTermination(LocalDate.of(2000,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.WORKING).socialSecurityNumber("951753").build();
		
		PersonalDocument personalDocument9 =
				PersonalDocument.builder().userId(9L).firstName("Serra").lastName("Türker").identityNumber(
						                "10235489675")
				                .dateOfBirth(LocalDate.of(1999,03,10)).mobileNumber("059545646546").address("Ankara")
				                .gender(EGender.FEMALE).email("kullanici9@gmail.com").position(EPosition.MANAGER)
				                .dateOfEmployment(LocalDate.of(2000,8,13))
				                .dateOfTermination(LocalDate.of(2000,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.WORKING).socialSecurityNumber("951712").build();
		
		PersonalDocument personalDocument10 =
				PersonalDocument.builder().userId(10L).firstName("Alparslan").lastName("Can").identityNumber(
						                "10235489675")
				                .dateOfBirth(LocalDate.of(1999,03,10)).mobileNumber("059545646546").address("İzmir")
				                .gender(EGender.MALE).email("kullanici10@gmail.com").position(EPosition.MANAGER)
				                .dateOfEmployment(LocalDate.of(2000,8,13))
				                .dateOfTermination(LocalDate.of(2000,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.WORKING).socialSecurityNumber("951756").build();
		
		PersonalDocument personalDocument11 =
				PersonalDocument.builder().userId(11L).firstName("Seda").lastName("Gönenç").identityNumber(
						                "10235489675")
				                .dateOfBirth(LocalDate.of(1999,03,10)).mobileNumber("059545646546").address("Muğla")
				                .gender(EGender.FEMALE).email("kullanici11@gmail.com").position(EPosition.SENIOR)
				                .dateOfEmployment(LocalDate.of(2000,8,13))
				                .dateOfTermination(LocalDate.of(2000,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.WORKING).socialSecurityNumber("123753").build();
		
		PersonalDocument personalDocument12 =
				PersonalDocument.builder().userId(12L).firstName("Burhaneddin").lastName("Meriç").identityNumber(
						                "10235489635")
				                .dateOfBirth(LocalDate.of(1988,8,10)).mobileNumber("059545646546").address("Antalya")
				                .gender(EGender.MALE).email("kullanici12@gmail.com").position(EPosition.TEAM_LEAD)
				                .dateOfEmployment(LocalDate.of(2008,5,13))
				                .dateOfTermination(LocalDate.of(2010,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.WORKING).socialSecurityNumber("951489").build();
		
		PersonalDocument personalDocument13 =
				PersonalDocument.builder().userId(13L).firstName("Aslı").lastName("Enver").identityNumber(
						                "10235483652")
				                .dateOfBirth(LocalDate.of(1975,03,10)).mobileNumber("059545646546").address("Ankara")
				                .gender(EGender.FEMALE).email("kullanici13@gmail.com").position(EPosition.MANAGER)
				                .dateOfEmployment(LocalDate.of(2000,8,13))
				                .dateOfTermination(LocalDate.of(2000,8,13)).annualSalary(9999.99)
				                .employmentStatus(EEmploymentStatus.WORKING).socialSecurityNumber("945753").build();
		
		return List.of(personalDocument1, personalDocument2, personalDocument3, personalDocument4, personalDocument5,personalDocument6,personalDocument7,personalDocument8, personalDocument9,personalDocument10,personalDocument11,personalDocument12,personalDocument13);
	}

}