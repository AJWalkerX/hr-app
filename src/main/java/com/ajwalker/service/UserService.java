package com.ajwalker.service;

import com.ajwalker.dto.request.DologinRequestDto;
import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.mapper.UserMapper;
import com.ajwalker.repository.UserRepository;
import com.ajwalker.utility.Enum.user.EUserState;
import com.ajwalker.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor // bunu yazmazsak constructor injection yapmak gerekir
public class UserService {
	private final UserRepository userRepository;
	private final JwtManager jwtManager;
	
	public void register(RegisterRequestDto dto) {
		User user = UserMapper.INSTANCE.fromRegisterDto(dto);
		user.setUserState(EUserState.ACTIVE);
		userRepository.save(user);
		
	}
	
	public String doLogin(DologinRequestDto dto) {
		Optional<User> userOptional = userRepository.findOptionalByEmailAndPassword(dto.email(),dto.password());
		if(userOptional.isEmpty())
			throw new HRAppException(ErrorType.INVALID_EMAIL_OR_PASSWORD);
		String token  = jwtManager.createToken(userOptional.get().getId());
		return token;
	}
	
}