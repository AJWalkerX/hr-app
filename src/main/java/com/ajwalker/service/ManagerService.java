package com.ajwalker.service;

import com.ajwalker.dto.request.*;
import com.ajwalker.dto.response.EmployeesResponseDto;
import com.ajwalker.dto.response.ManagerSpendingResponseDto;
import com.ajwalker.dto.response.UserPermitResponseDto;
import com.ajwalker.entity.Company;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.PersonalSpending;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.utility.Enum.EState;
import com.ajwalker.utility.Enum.company.ECompanyType;
import com.ajwalker.utility.Enum.company.ERegion;
import com.ajwalker.utility.Enum.holiday.EHolidayState;
import com.ajwalker.utility.Enum.personalSpending.ESpendingState;
import com.ajwalker.utility.Enum.user.EEmploymentStatus;
import com.ajwalker.utility.Enum.user.EGender;
import com.ajwalker.utility.Enum.user.EPosition;
import com.ajwalker.utility.Enum.user.EUserState;
import com.ajwalker.utility.JwtManager;
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
    private final MailService mailService;
    private final SalaryService salaryService;
    private final PersonalSpendingService personalSpendingService;
    private final JwtManager jwtManager;
    private final CompanyService companyService;
    
    
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
    
    
    
    
    public List<UserPermitResponseDto> getUserPermitList(Long managerId){
        return userService.getUserPermitList(managerId);
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
        
        personalDocument.setEmail(dto.email());
        personalDocument.setAddress(dto.address());
        personalDocument.setAnnualSalary(dto.annualSalary());
        personalDocument.setDateOfBirth(dto.dateOfBirth());
        personalDocument.setDateOfEmployment(dto.dateOfEmployment());
        personalDocument.setFirstName(dto.firstName());
        personalDocument.setLastName(dto.lastName());
        personalDocument.setGender(EGender.valueOf(dto.gender().toUpperCase()));
        personalDocument.setIdentityNumber(dto.identityNumber());
        personalDocument.setSocialSecurityNumber(dto.socialSecurityNumber());
        personalDocument.setMobileNumber(dto.mobileNumber());
        personalDocument.setPosition(EPosition.valueOf(dto.position().toUpperCase()));
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
        if (!dto.password().equals(dto.rePassword())){
            throw new HRAppException(ErrorType.PASSWORD_ERROR);
        }

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
        salaryService.generateMonthlySalaries(user.getId());
        mailService.sendMail(user.getEmail(), "Yeni Calisan bilgileri", "mail adresinizi kullanarak ve asagidaki sifreyi kullanarak http://localhost:3000/login adresinden giris yapabilirsiniz" +
                "\n Sifre: " + dto.password());
        
        return true;
    }
    
    public List<ManagerSpendingResponseDto> employeeListBySpending(Long userId) {
        Optional<User> optUser = userService.findById(userId);
        if (optUser.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_USER);
        }
        User user = optUser.get();
        Long companyId = user.getCompanyId();
        List<User> companyEmployees = userService.findUsersByCompanyId(companyId);
        List<Long> employeeIds = companyEmployees.stream().map(User::getId).toList();
        
        Map<Long, PersonalDocument> personalDocumentMap = personalDocumentService.findByUserIdList(employeeIds);
        Map<Long, List<PersonalSpending>> personalSpendingMap = personalSpendingService.findByUserIdList(employeeIds);
        
        return companyEmployees.stream()
                               .filter(employee -> personalSpendingMap.containsKey(employee.getId())) // Harcaması olmayanları çıkar
                               .map(employee -> {
                                   PersonalDocument personalDocument = personalDocumentMap.get(employee.getId());
                                   List<PersonalSpending> personalSpendings = personalSpendingMap.get(employee.getId());
                                   
                                   // Tüm harcamaları DTO'ya ekliyoruz
                                   List<ManagerSpendingResponseDto.SpendingDetails> spendingDetailsList = personalSpendings.stream()
                                                                                                                           .map(spending -> new ManagerSpendingResponseDto.SpendingDetails(
                                                                                                                                   spending.getSpendingDate(),
                                                                                                                                   spending.getDescription(),
                                                                                                                                   spending.getSpendingType().toString(),
                                                                                                                                   spending.getId(),
                                                                                                                                   spending.getBillAmount()
                                                                                                                           ))
                                                                                                                           .toList();
                                   
                                   return new ManagerSpendingResponseDto(
                                           employee.getId(),
                                           companyId,
                                           employee.getAvatar(),
                                           personalDocument.getFirstName(),
                                           personalDocument.getLastName(),
                                           spendingDetailsList // Tüm harcama detayları ekleniyor
                                   );
                               }).toList();
    }
    
    
    public Boolean authorizeSpending(SpendingAuthorizeRequestDto dto) {
        if (dto.answer().equalsIgnoreCase(String.valueOf(ESpendingState.APPROVED))){
            personalSpendingService.approveEmployeeSpending(dto.spendingId());
            Optional<PersonalSpending> optSpending = personalSpendingService.findById(dto.spendingId());
            if (optSpending.isEmpty()){
                throw new HRAppException(ErrorType.NOTFOUND_SPENDING);
            }
            System.out.println("user idmiz: "+dto.userId());
            salaryService.addSpendingToSalary(dto.userId(),optSpending.get());
            return true;
        }
        else if (dto.answer().equalsIgnoreCase(String.valueOf(ESpendingState.REJECTED))) {
            personalSpendingService.rejectEmployeeSpending(dto.spendingId());
            return true;
            
        }
        else return false;
    }
    
    public Boolean updateManager(UpdateManagerRequestDto dto) {
        Optional<Long> optManagerId = jwtManager.verifyToken(dto.token());
        if (optManagerId.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        Optional<User> userOpt = userService.findById(optManagerId.get());
        User manager = userOpt.get();
        Optional<Company> companyOptional = companyService.findById(manager.getCompanyId());
        if (companyOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_COMPANY);
        }
        Company company = companyOptional.get();
        Optional<PersonalDocument> optPersonalDocument = personalDocumentService.findByUserId(manager.getId());
        PersonalDocument personalDocument = optPersonalDocument.get();
        
        // Avatar bilgisini User'dan güncelleme
        manager.setAvatar(dto.avatar());
        
        // PersonalDocument'ten gelen bilgileri güncelleme
        personalDocument.setIdentityNumber(dto.identityNumber());
        personalDocument.setDateOfBirth(dto.dateOfBirth());
        personalDocument.setMobileNumber(dto.mobileNumber());
        personalDocument.setAddress(dto.address());
        
        // String'ten enum'a çevirme işlemi
        
            personalDocument.setGender(EGender.valueOf(dto.gender()));
            
        
        personalDocument.setDateOfEmployment(dto.dateOfEmployment());
        personalDocument.setSocialSecurityNumber(dto.socialSecurityNumber());
        
        // Şirket bilgilerini güncelleme

        company.setCompanyType(ECompanyType.valueOf(dto.companyType()));
        company.setRegion(ERegion.valueOf(dto.region()));
       
           
        
        company.setCompanyMail(dto.companyMail());
        company.setCompanyLogo(dto.companyLogo());
        company.setCompanyAddress(dto.companyAddress());
        company.setTelNo(dto.telNo());
        
        // Güncellenen verileri kaydetme
        userService.save(manager);
        personalDocumentService.save(personalDocument);
        companyService.save(company);
        
        return true;
    }
    
    
}