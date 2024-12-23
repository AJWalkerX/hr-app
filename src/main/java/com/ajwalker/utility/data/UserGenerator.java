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
				.companyId(6L)
				.email("kullanici1@gmail.com")
				.password(passwordEncoder.encode("Aaa12345!"))
				.avatar("https://b6s54eznn8xq.merlincdn.net/Uploads/Films/gladyator-2-20241113144046c09c0a34acc34dbba257e687e2e14339.jpg")
				.userState(EUserState.IN_REVIEW)
				.isFirstLogin(true)
				.build();
		
		User user2 = User.builder()
		                 .companyId(7L)
				.email("kullanici2@gmail.com")
				.password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://b6s54eznn8xq.merlincdn.net/Uploads/ImageEntries/Event/48651/Kapak-3270ba3742ac43c0b03f2e248ee6e7bd.jpg")
		                 .userState(EUserState.IN_REVIEW)
		                 .isFirstLogin(true)
		                 .build();
		
		User user3 = User.builder()
		                 .companyId(8L)
				.email("kullanici3@gmail.com")
				.password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://b6s54eznn8xq.merlincdn.net/Uploads/Films/100-kurt-202411141334544b1af1c97efa48ffb19793f21a3bbbe1.jpg")
		                 .userState(EUserState.IN_REVIEW)
		                 .isFirstLogin(true)
		                 .build();
		
		User user4 = User.builder()
		                 .companyId(9L)
				.email("kullanici4@gmail.com")
				.password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://b6s54eznn8xq.merlincdn.net/Uploads/Films/illegal-hayatlar-meclis-20241023152858df83c1bb7b1444d5807b348eab5878bf.jpg")
		                 .userState(EUserState.IN_REVIEW)
		                 .isFirstLogin(true)
		                 .build();
		
		User user5 = User.builder()
		                 .companyId(10L)
				.email("kullanici5@gmail.com")
				.password(passwordEncoder.encode("Aaa12345!"))
		                 .avatar("https://b6s54eznn8xq.merlincdn.net/Uploads/Films/siyah-kanarya-2024111414245316947b81fe20494c8f01dd26cb2c7514.jpg")
		                 .userState(EUserState.IN_REVIEW)
		                 .isFirstLogin(true)
		                 .build();
		
		return List.of(user1, user2, user3, user4, user5);
	}

	
}