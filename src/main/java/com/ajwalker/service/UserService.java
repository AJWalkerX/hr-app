package com.ajwalker.service;

import com.ajwalker.constant.MailApis;
import com.ajwalker.dto.request.DologinRequestDto;
import com.ajwalker.dto.request.NewPasswordRequestDto;
import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.dto.request.UserAuthorisationDto;
import com.ajwalker.dto.response.UserOnWaitInfoResponseDto;
import com.ajwalker.entity.Company;
import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.mapper.UserMapper;
import com.ajwalker.repository.UserRepository;
import com.ajwalker.utility.Enum.user.EUserAuthorisation;
import com.ajwalker.utility.Enum.user.EUserState;
import com.ajwalker.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // bunu yazmazsak constructor injection yapmak gerekir
public class UserService {
	private final UserRepository userRepository;
	private final MailService mailService;
	private final UserAuthVerifyCodeService userAuthVerifyCodeService;
	private final JwtManager jwtManager;
	private final PasswordEncoder passwordEncoder;
	private final PersonalDocumentService personalDocumentService;
	private final CompanyService companyService;
	private final MemberShipPlanService memberShipPlanService;

	
	public Boolean register(RegisterRequestDto dto) {
		User user = UserMapper.INSTANCE.fromRegisterDto(dto);
		user.setUserState(EUserState.PENDING);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);

		personalDocumentService.createPersonalDocument(dto.personalRole(), dto.name(), dto.surname(), dto.email(), user.getId());
		Company company = companyService.createCompany(dto.companyName());
		user.setCompanyId(company.getId());
		user = userRepository.save(user);

		String authCode = userAuthVerifyCodeService.generateUserAuthVerifyCode(user.getId());
		String verificationLink = MailApis.VERIFICATION_LINK + authCode;
		mailService.sendMail(user.getEmail(), MailApis.VERIFICATION, verificationLink);
		return true;
	}
	
	public String doLogin(DologinRequestDto dto) {

		Optional<User> userOptional = userRepository.findOptionalByEmail(dto.email());

		if (userOptional.isEmpty()) {
			throw new HRAppException(ErrorType.INVALID_EMAIL_OR_PASSWORD);
		}

		if(!passwordEncoder.matches(dto.password(), userOptional.get().getPassword())){
			throw new HRAppException(ErrorType.INVALID_EMAIL_OR_PASSWORD);
		}
		if(userOptional.get().getUserState().equals(EUserState.PENDING)){
			throw new HRAppException(ErrorType.PENDING_USER);
		}
		if(userOptional.get().getUserState().equals(EUserState.IN_REVIEW)){
			throw new HRAppException(ErrorType.IN_REVIEW_USER);
		}
		if(userOptional.get().getUserState().equals(EUserState.DENIED)){
			throw new HRAppException(ErrorType.DENIED_USER);
		}
        return jwtManager.createToken(userOptional.get().getId());
	}

	public Optional<User> findUserById(Long userId) {
		return userRepository.findById(userId);
	}

	public void updateUserStateToInReView(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			user.setUserState(EUserState.IN_REVIEW);
			userRepository.save(user);
		}
	}

//	//TODO CompanyServicede yapılacak Admin servicede yapılacak
//	public List<User> getAllCustomers() {
//		List<User> allUserByUserState = userRepository.findAllUserByUserState(List.of(EUserState.ACTIVE, EUserState.INACTIVE));
//		return allUserByUserState.stream().map(user -> {
//
//			Company company = companyService
//					.getCompanyById(user.getCompanyId());
//
//			MemberShipPlan memberShipPlan = memberShipPlanService
//					.findById(company.getId());
//
//
//
//		}).collect(Collectors.toList());
//	}

	public List<UserOnWaitInfoResponseDto> getAllUserOnWait() {
		List<User> allUserByUserState = userRepository.findAllUserByUserState(List.of(EUserState.PENDING, EUserState.IN_REVIEW));
		return allUserByUserState.stream().map(users->{

			PersonalDocument personalDocument = personalDocumentService
					.personalFindById(users.getId());

			Company company = companyService
					.getCompanyById(users.getCompanyId());

			return new UserOnWaitInfoResponseDto(
					personalDocument.getFirstName(),
					personalDocument.getLastName(),
					personalDocument.getEmail(),
					personalDocument.getPosition().toString(),
					company.getCompanyName()
			);
		}).collect(Collectors.toList());

	}

	public User userAuthorisation(UserAuthorisationDto dto) {
		Optional<User> userOptional = userRepository.findById(dto.userId());
		if(userOptional.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		User user = userOptional.get();
		if(dto.answer().equalsIgnoreCase(EUserAuthorisation.ACCEPT.toString())) {
			return updateUserToInActive(user);
		}
		if(dto.answer().equalsIgnoreCase(EUserAuthorisation.DENY.toString())) {
			return updateUserToDenied(user);
		}
		return user;
	}
	private User updateUserToInActive(User user) {
		user.setUserState(EUserState.INACTIVE);
		return userRepository.save(user);
	}
	private User updateUserToDenied(User user) {
		user.setUserState(EUserState.DENIED);
		return userRepository.save(user);
	}

	public Boolean forgotPasswordMail(String email) {
		Optional<User> userOptional = userRepository.findOptionalByEmail(email);
		if (userOptional.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		User user = userOptional.get();
		String authCode = userAuthVerifyCodeService.generateUserAuthVerifyCode(user.getId());
		String verificationLink = MailApis.NEW_PASSWORD_VERIFICATION_LINK + authCode;
		mailService.sendMail(user.getEmail(), MailApis.VERIFICATION, verificationLink);
		return true;

	}

	public boolean updateUserPassword(NewPasswordRequestDto dto) {
		Optional<Long> userIdByAuthCode = userAuthVerifyCodeService.findUserIdByAuthCode(dto.authCode());
		System.out.println(dto.authCode());
		if(userIdByAuthCode.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		Optional<User> userOptional = userRepository.findById(userIdByAuthCode.get());
		if (userOptional.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		User user = userOptional.get();
		user.setPassword(passwordEncoder.encode(dto.password()));
		userRepository.save(user);
		return true;
	}
}