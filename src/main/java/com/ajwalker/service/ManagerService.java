package com.ajwalker.service;

import com.ajwalker.dto.request.AddEmployeeRequestDto;
import com.ajwalker.dto.request.HolidayAuthorizeRequestDto;
import com.ajwalker.dto.request.IUpdateEmployeeRequestDto;
import com.ajwalker.dto.response.EmployeesResponseDto;
import com.ajwalker.dto.response.UserPermitResponseDto;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.mapper.PersonalDocumentMapper;
import com.ajwalker.utility.Enum.EState;
import com.ajwalker.utility.Enum.holiday.EHolidayState;
import com.ajwalker.utility.Enum.user.EEmploymentStatus;
import com.ajwalker.utility.Enum.user.EGender;
import com.ajwalker.utility.Enum.user.EPosition;
import com.ajwalker.utility.Enum.user.EUserState;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final PersonalDocumentService personalDocumentService;
    private final UserService userService;
    private final WorkHolidayService workHolidayService;
    private final PasswordEncoder getPasswordEncoder;


    public List<EmployeesResponseDto> employeeListByCompany(Long userId) {
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_USER);
        }
        User user = optionalUser.get();
        Long companyId = user.getCompanyId();
        List<User> companyEmployees = userService.findUsersByCompanyId(companyId);
        List<Long> employeeIds = companyEmployees.stream().map(User::getId).toList();
        Map<Long, PersonalDocument> personalDocumentMap = personalDocumentService.findByUserIdList(employeeIds);

        return companyEmployees.stream().map(employee -> {
            PersonalDocument personalDocument = personalDocumentMap.get(employee.getId());
            return new EmployeesResponseDto(
                    companyId,
                    employee.getId(),
                    employee.getAvatar(),
                    employee.getEmail(),
                    personalDocument.getAddress(),
                    personalDocument.getAnnualSalary(),
                    personalDocument.getDateOfBirth(),
                    personalDocument.getDateOfEmployment(),
                    personalDocument.getDateOfTermination(),
                    personalDocument.getFirstName(),
                    personalDocument.getLastName(),
                    personalDocument.getGender() == null ? EGender.UNDEFINED.name() : personalDocument.getGender().toString(),
                    personalDocument.getIdentityNumber(),
                    personalDocument.getSocialSecurityNumber(),
                    personalDocument.getMobileNumber(),
                    personalDocument.getPosition().toString(),
                    personalDocument.getEmploymentStatus() == null ? EEmploymentStatus.UNDEFINED.name() : personalDocument.getEmploymentStatus().toString()
            );
        }).toList();

    }
    
    
    
    
    public List<UserPermitResponseDto> getUserPermitList(){
        return userService.getUserPermitList();
    }


    public Boolean authorizePermit(HolidayAuthorizeRequestDto dto) {
        if (dto.answer().equalsIgnoreCase(String.valueOf(EHolidayState.APPROVED))){
            workHolidayService.approveEmployeeHoliday(dto.workHolidayId());
            return true;
        }
        else if (dto.answer().equalsIgnoreCase(String.valueOf(EHolidayState.REJECTED))){
            workHolidayService.rejectEmployeeHoliday(dto.workHolidayId());
            return true;
        }
        else return false;
    }
    
    public Boolean updateEmployee(IUpdateEmployeeRequestDto dto) {
        Optional<User> optionalUser = userService.findUserById(dto.userId());
        if (optionalUser.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_USER);
        }
        PersonalDocument personalDocument = personalDocumentService.findPersonalByUserId(dto.userId());
        
        personalDocument = PersonalDocumentMapper.INSTANCE.fromIUpdateEmployeeRequestDto(dto, personalDocument);
        personalDocumentService.save(personalDocument);
        
        return true;
        
    }

    public Boolean deleteEmployee(Long userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_USER);
        }
        User user = userOptional.get();
        user.setState(EState.PASSIVE);
        userService.save(user);
        return true;
    }

    public Boolean addNewEmployee(AddEmployeeRequestDto dto, Long managerId) {
        User manager = userService.findUserById(managerId).orElse(null);
        if (manager == null) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }

//         PersonalDocument icerisindeki employment status working olarak gelecek!

        User user = User.builder()
                .email(dto.email())
                .password(getPasswordEncoder.encode(dto.password()))
                .userState(EUserState.ACTIVE)
                .isFirstLogin(false)
                .companyId(manager.getCompanyId())
                .build();
        user = userService.save(user);
        PersonalDocument personalDocument = PersonalDocument.builder()
                .userId(user.getId())
                .email(dto.email())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .address(dto.address())
                .annualSalary(dto.annualSalary())
                .dateOfBirth(dto.dateOfBirth())
                .dateOfEmployment(dto.dateOfEmployment())
                .employmentStatus(EEmploymentStatus.WORKING)
                .identityNumber(dto.identityNumber())
                .socialSecurityNumber(dto.socialSecurityNumber())
                .mobileNumber(dto.mobileNumber())
        .build();
        switch (dto.position().toUpperCase()){
            case "INTERN":
                personalDocument.setPosition(EPosition.INTERN);
                break;
            case "JUNIOR":
                personalDocument.setPosition(EPosition.JUNIOR);
                break;
            case "MID LEVEL":
                personalDocument.setPosition(EPosition.MID_LEVEL);
                break;
            case "SENIOR":
                personalDocument.setPosition(EPosition.SENIOR);
                break;
            case "TEAM LEAD":
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
        }
        switch (dto.gender().toUpperCase()){
            case "MALE":
                personalDocument.setGender(EGender.MALE);
                break;
                case "FEMALE":
                personalDocument.setGender(EGender.FEMALE);
                break;
            default:
                personalDocument.setGender(EGender.OTHER);
        }
        personalDocumentService.save(personalDocument);
        return true;
    }
}