package com.ajwalker.utility.data;

import com.ajwalker.entity.User;
import com.ajwalker.utility.Enum.user.EUserState;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
public class UserGenerator {


	public static List<User> generateUser(){
		//Kullanici sifresi Aaa12345!
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user1 = User.builder()
				.companyId(3L)
				.email("kullanici1@gmail.com")
				.password(passwordEncoder.encode("Aaa12345!"))
				.avatar("https://mighty.tools/mockmind-api/content/human/104.jpg")
				.userState(EUserState.ACTIVE)
				.isFirstLogin(true)
				.build();
		
		User user2 = User.builder()
		                 .companyId(5L)
				.email("kullanici2@gmail.com")
				.password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://mighty.tools/mockmind-api/content/human/80.jpg")
		                 .userState(EUserState.IN_REVIEW)
		                 .isFirstLogin(true)
		                 .build();
		
		User user3 = User.builder()
		                 .companyId(5L)
				.email("kullanici3@gmail.com")
				.password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://mighty.tools/mockmind-api/content/human/122.jpg")
		                 .userState(EUserState.IN_REVIEW)
		                 .isFirstLogin(true)
		                 .build();
		
		User user4 = User.builder()
		                 .companyId(3L)
				.email("kullanici4@gmail.com")
				.password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://mighty.tools/mockmind-api/content/human/92.jpg")
		                 .userState(EUserState.ACTIVE)
		                 .isFirstLogin(true)
		                 .build();
		
		User user5 = User.builder()
		                 .companyId(5L)
				.email("kullanici5@gmail.com")
				.password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://mighty.tools/mockmind-api/content/human/43.jpg")
		                 .userState(EUserState.IN_REVIEW)
		                 .isFirstLogin(true)
		                 .build();
		User user6 = User.builder()
		                 .companyId(1L)
		                 .email("kullanici6@gmail.com")
		                 .password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://mighty.tools/mockmind-api/content/human/97.jpg")
		                 .userState(EUserState.IN_REVIEW)
		                 .isFirstLogin(true)
		                 .build();
		User user7 = User.builder()
		                 .companyId(5L)
		                 .email("kullanici7@gmail.com")
		                 .password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://mighty.tools/mockmind-api/content/human/123.jpg")
		                 .userState(EUserState.ACTIVE)
		                 .isFirstLogin(true)
		                 .build();
		User user8 = User.builder()
		                 .companyId(3L)
		                 .email("kullanici8@gmail.com")
		                 .password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://mighty.tools/mockmind-api/content/human/75.jpg")
		                 .userState(EUserState.ACTIVE)
		                 .isFirstLogin(true)
		                 .build();
		User user9 = User.builder()
		                 .companyId(1L)
		                 .email("kullanici9@gmail.com")
		                 .password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://mighty.tools/mockmind-api/content/human/38.jpg")
		                 .userState(EUserState.ACTIVE)
		                 .isFirstLogin(true)
		                 .build();
		User user10 = User.builder()
		                 .companyId(2L)
		                 .email("kullanici10@gmail.com")
		                 .password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://mighty.tools/mockmind-api/content/human/4.jpg")
		                 .userState(EUserState.ACTIVE)
		                 .isFirstLogin(true)
		                 .build();
		User user11 = User.builder()
		                 .companyId(3L)
		                 .email("kullanici11@gmail.com")
		                 .password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://mighty.tools/mockmind-api/content/human/111.jpg")
		                 .userState(EUserState.ACTIVE)
		                 .isFirstLogin(true)
		                 .build();
		User user12 = User.builder()
		                 .companyId(4L)
		                 .email("kullanici12@gmail.com")
		                 .password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://mighty.tools/mockmind-api/content/human/93.jpg")
		                 .userState(EUserState.ACTIVE)
		                 .isFirstLogin(true)
		                 .build();
		User user13 = User.builder()
		                 .companyId(4L)
		                 .email("kullanici13@gmail.com")
		                 .password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://mighty.tools/mockmind-api/content/human/125.jpg")
		                 .userState(EUserState.ACTIVE)
		                 .isFirstLogin(true)
		                 .build();
		
		
		return List.of(user1, user2, user3, user4, user5,user6,user7,user8,user9,user9,user10,user11,user12,user13);
	}

	
}