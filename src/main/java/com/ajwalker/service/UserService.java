package com.ajwalker.service;

import com.ajwalker.constant.MailApis;
import com.ajwalker.dto.request.*;
import com.ajwalker.dto.response.GetUserProfileInfoDto;
import com.ajwalker.dto.response.LoginResponseDto;
import com.ajwalker.dto.response.UserOnWaitInfoResponseDto;
import com.ajwalker.dto.response.UserPermitResponseDto;
import com.ajwalker.entity.Company;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.User;
import com.ajwalker.entity.WorkHoliday;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.mapper.UserMapper;
import com.ajwalker.repository.UserRepository;
import com.ajwalker.utility.Enum.company.ECompanyType;
import com.ajwalker.utility.Enum.company.ERegion;
import com.ajwalker.utility.Enum.user.EGender;
import com.ajwalker.utility.Enum.user.EPosition;
import com.ajwalker.utility.Enum.user.EUserState;
import com.ajwalker.utility.JwtManager;
import com.ajwalker.view.VwPermitUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private final WorkHolidayService workHolidayService;

	
	public Boolean register(RegisterRequestDto dto) {
		Optional<Company> companyOptional = companyService.findCompanyByCompanyName(dto.companyName());
		if (companyOptional.isPresent()) {
			throw new HRAppException(ErrorType.ALREADY_EXIST_COMPANY);
		}
		User user = UserMapper.INSTANCE.fromRegisterDto(dto);
		user.setUserState(EUserState.PENDING);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setIsFirstLogin(true);
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
	
	public LoginResponseDto doLogin(DologinRequestDto dto) {
		Optional<User> userOptional = userRepository.findOptionalByEmail(dto.email());
		if (userOptional.isEmpty()) {
			throw new HRAppException(ErrorType.INVALID_EMAIL_OR_PASSWORD);
		}
		Optional<PersonalDocument> personalDocumentOptional = personalDocumentService.findByUserId(userOptional.get().getId());
		if (personalDocumentOptional.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_PERSONALDOCUMENT);
		}

		User user = userOptional.get();
		if(!passwordEncoder.matches(dto.password(), user.getPassword())){
			throw new HRAppException(ErrorType.INVALID_EMAIL_OR_PASSWORD);
		}
		if(user.getUserState().equals(EUserState.PENDING)){
			throw new HRAppException(ErrorType.PENDING_USER);
		}
		if(user.getUserState().equals(EUserState.IN_REVIEW)){
			throw new HRAppException(ErrorType.IN_REVIEW_USER);
		}
		if(user.getUserState().equals(EUserState.DENIED)){
			throw new HRAppException(ErrorType.DENIED_USER);
		}
		boolean isFirstLogin = user.getIsFirstLogin();
		if (user.getIsFirstLogin()) {
			user.setIsFirstLogin(false);
			user = userRepository.save(user);
		}
        return new LoginResponseDto(jwtManager.createToken(user.getId()), isFirstLogin, personalDocumentOptional.get().getPosition().toString());
	}

	public Optional<User> findUserById(Long userId) {
		return userRepository.findById(userId);
	}

	public void updateUserStateToInReView(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if(userOptional.isPresent()) {
		  companyService.updateCompanyInReview(userOptional.get().getCompanyId());
			User user = userOptional.get();
			user.setUserState(EUserState.IN_REVIEW);
			userRepository.save(user);
		}
	}







	public void updateUserToActive(User user) {
		user.setUserState(EUserState.ACTIVE);
		userRepository.save(user);
	}
	public void updateUserToDenied(User user) {
		user.setUserState(EUserState.DENIED);
		userRepository.save(user);
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
	
	public GetUserProfileInfoDto getUserProfileInfo(Long userId) {
		User user = userRepository.findById(userId).get();
		Optional<PersonalDocument> optionalPersonalDocument = personalDocumentService.findByUserId(userId);
		if(optionalPersonalDocument.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_PERSONALDOCUMENT);
		}
		PersonalDocument personalDocument = optionalPersonalDocument.get();
		return new GetUserProfileInfoDto(
				user.getAvatar(),
				personalDocument.getFirstName(),
				personalDocument.getLastName(),
				personalDocument.getIdentityNumber(),
				personalDocument.getDateOfBirth(),
				personalDocument.getMobileNumber(),
				personalDocument.getAddress(),
				personalDocument.getGender().toString(),
				personalDocument.getEmail(),
				personalDocument.getPosition(),
				personalDocument.getDateOfEmployment(),
				personalDocument.getSocialSecurityNumber()
		);
	}

	public Optional<User> findById(Long userId) {
		return userRepository.findById(userId);
	}

	public List<User> findAllUserByUserState(List<EUserState> inReview) {
		return userRepository.findAllUserByUserState(inReview);
	}
	
	public List<UserPermitResponseDto> getUserPermitList() {
		
		List<WorkHoliday> allWorkHolidaysInPending = workHolidayService.findAllWorkHolidaysInPending();
		if (allWorkHolidaysInPending.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_WORKHOLIDAY_INPENDING);
		}
		List<Long> userIdList = allWorkHolidaysInPending.stream().map(WorkHoliday::getUserId).toList();
		Map<Long, VwPermitUser> vwPermitUserMap = findAllUsersByUserIds(userIdList);
		Map<Long, PersonalDocument> personalDocumentMap = personalDocumentService.findPersonalDocumentByUserIds(userIdList);

		return allWorkHolidaysInPending.stream()
				.map(wh -> {
					VwPermitUser user = vwPermitUserMap.get(wh.getUserId());
					PersonalDocument pd = personalDocumentMap.get(wh.getUserId());
					return new UserPermitResponseDto(
							user.userId(),
							wh.getId(),
							user.avatar(),
							pd.getFirstName(),
							pd.getLastName(),
							pd.getPosition().toString(),
							wh.getBeginDate(),
							wh.getEndDate(),
							wh.getDescription(),
							wh.getHolidayType().toString(),
							wh.getHolidayState().toString()
					);
				})
				.toList();

	
	}

	private Map<Long, VwPermitUser> findAllUsersByUserIds(List<Long> userIdList) {
		Map<Long, VwPermitUser> vwPermitUserMap = new HashMap<>();
		List<VwPermitUser> allUsersByUserIds = userRepository.findAllUsersByUserIds(userIdList);
		for(VwPermitUser user : allUsersByUserIds) {
			vwPermitUserMap.put(user.userId(), user);
		}
		return vwPermitUserMap;
	}


	public Boolean createWorkHoliday(WorkHolidayRequestDto dto, Long userId) {
		return workHolidayService.createWorkHoliday(dto, userId);
	}

	public List<User> findUserInfo(Long companyId) {
		return userRepository.findUsersByCompanyId(companyId);
	}

	public Boolean updateUserInformation(FirstLoginInformationDto dto, Long userId) {
		Optional<User> optionalUserId = userRepository.findById(userId);
		if(optionalUserId.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_USER);
		}
		User user = optionalUserId.get();
		user.setEmail(dto.mail());
		user = userRepository.save(user);
		Optional<PersonalDocument> personalDocumentOptional = personalDocumentService.findByUserId(user.getId());
		if(personalDocumentOptional.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_PERSONALDOCUMENT);
		}
		PersonalDocument personalDocument = personalDocumentOptional.get();
		personalDocument.setFirstName(dto.firstName());
		personalDocument.setLastName(dto.lastName());
		personalDocument.setIdentityNumber(dto.identityNumber());
		personalDocument.setDateOfBirth(dto.dateOfBirth());
		personalDocument.setMobileNumber(dto.mobile());
		personalDocument.setAddress(dto.address());
		if(dto.gender().equalsIgnoreCase(EGender.MALE.toString())) {
			personalDocument.setGender(EGender.MALE);
		}
		if(dto.gender().equalsIgnoreCase(EGender.FEMALE.toString())) {
			personalDocument.setGender(EGender.FEMALE);
		}
		personalDocument.setDateOfEmployment(dto.dateOfBeginToWork());
		personalDocument.setSocialSecurityNumber(dto.socialSecurityNumber());
		switch (dto.companySector().toUpperCase()) {
			case "INTERN":
				personalDocument.setPosition(EPosition.INTERN);
				break;
			case "JUNIOR":
				personalDocument.setPosition(EPosition.JUNIOR);
				break;
			case "MID_LEVEL":
				personalDocument.setPosition(EPosition.MID_LEVEL);
				break;
			case "SENIOR":
				personalDocument.setPosition(EPosition.SENIOR);
				break;
			case "TEAM_LEAD":
				personalDocument.setPosition(EPosition.TEAM_LEAD);
				break;
			case "MANAGER":
				personalDocument.setPosition(EPosition.MANAGER);
				break;
			case "DIRECTOR":
				personalDocument.setPosition(EPosition.DIRECTOR);
				break;
			default:
				personalDocument.setPosition(EPosition.NONE);
				break;
		}

		personalDocumentService.save(personalDocument);
		Optional<Company> companyOptional = companyService.findById(user.getCompanyId());
		if(companyOptional.isEmpty()) {
			throw new HRAppException(ErrorType.NOTFOUND_COMPANY);
		}
		Company company = companyOptional.get();
		company.setCompanyName(dto.companyName());
		company.setCompanyMail(dto.companyMail());
		company.setTelNo(dto.companyPhoneNumber());
		switch (dto.companySector().toUpperCase()) {
			case "TECHNOLOGY":
				company.setCompanyType(ECompanyType.TECHNOLOGY);
				break;
			case "FOOD":
				company.setCompanyType(ECompanyType.FOOD);
				break;
			case "HEALTHCARE":
				company.setCompanyType(ECompanyType.HEALTHCARE);
				break;
			case "EDUCATION":
				company.setCompanyType(ECompanyType.EDUCATION);
				break;
			case "CONSTRUCTION":
				company.setCompanyType(ECompanyType.CONSTRUCTION);
				break;
			case "FINANCE":
				company.setCompanyType(ECompanyType.FINANCE);
				break;
			case "ENERGY":
				company.setCompanyType(ECompanyType.ENERGY);
				break;
			case "TOURISM":
				company.setCompanyType(ECompanyType.TOURISM);
				break;
			case "MEDIA_AND_ENTERTAINMENT":
				company.setCompanyType(ECompanyType.MEDIA_AND_ENTERTAINMENT);
				break;
			case "AUTOMOTIVE":
				company.setCompanyType(ECompanyType.AUTOMOTIVE);
				break;
			case "FASHION_AND_TEXTILE":
				company.setCompanyType(ECompanyType.FASHION_AND_TEXTILE);
				break;
			default:
				company.setCompanyType(ECompanyType.UNKNOWN);
				break;
		}
		company.setRegion(ERegion.TURKEY);

		return true;

	}
	
	public void save(User user) {
		userRepository.save(user);
	}
}