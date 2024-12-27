package com.ajwalker.service;

import com.ajwalker.dto.request.HolidayAuthorizeRequestDto;
import com.ajwalker.dto.request.IUpdateEmployeeRequestDto;
import com.ajwalker.dto.response.EmployeesResponseDto;
import com.ajwalker.dto.response.UserPermitResponseDto;
import com.ajwalker.entity.Company;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.User;
import com.ajwalker.entity.WorkHoliday;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.mapper.PersonalDocumentMapper;
import com.ajwalker.mapper.UserMapper;
import com.ajwalker.repository.CompanyRepository;
import com.ajwalker.repository.PersonalDocumentRepository;
import com.ajwalker.utility.Enum.holiday.EHolidayState;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Manager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final PersonalDocumentService personalDocumentService;
    private final UserService userService;
    private final WorkHolidayService workHolidayService;
    private final PersonalDocumentRepository personalDocumentRepository;
    
    
    public List<EmployeesResponseDto> employeeListByCompany(Long companyId) {
        // Şirketin tüm kullanıcılarını al.
        List<User> users = userService.findUserInfo(companyId);
        
        if (users.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        
        // Kullanıcıların dökümantasyon bilgilerini toplayarak DTO listesi oluştur.
        return users.stream()
                    .map(user -> {
                        Optional<PersonalDocument> optionalPersonalDocument = personalDocumentService.findByUserId(user.getId());
                        
                        // Eğer dökümantasyon eksikse, eksik bilgileri null olarak işleyebiliriz.
                        PersonalDocument personalDocument = optionalPersonalDocument.orElse(null);
                        
                        return new EmployeesResponseDto(
                                user.getCompanyId(),
                                user.getId(),
                                user.getAvatar(),
                                user.getEmail(),
                                personalDocument != null ? personalDocument.getAddress() : null,
                                personalDocument != null ? personalDocument.getAnnualSalary() : null,
                                personalDocument != null ? personalDocument.getDateOfBirth() : null,
                                personalDocument != null ? personalDocument.getDateOfEmployment() : null,
                                personalDocument != null ? personalDocument.getDateOfTermination() : null,
                                personalDocument != null ? personalDocument.getFirstName() : null,
                                personalDocument != null ? personalDocument.getLastName() : null,
                                personalDocument != null ? personalDocument.getGender().toString() : null,
                                personalDocument != null ? personalDocument.getIdentityNumber() : null,
                                personalDocument != null ? personalDocument.getSocialSecurityNumber() : null,
                                personalDocument != null ? personalDocument.getMobileNumber() : null,
                                personalDocument != null ? personalDocument.getPosition().toString() : null,
                                personalDocument != null ? personalDocument.getEmploymentStatus().toString() : null
                        );
                    })
                    .collect(Collectors.toList());
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
    
    public EmployeesResponseDto updateEmployee(IUpdateEmployeeRequestDto dto) {
        Optional<User> optionalUser = userService.findUserById(dto.userId());
        if (optionalUser.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_USER);
        }
        User user = optionalUser.get();
 
        
        PersonalDocument personalDocument = personalDocumentService.personalFindById(dto.userId());
        
        personalDocument = PersonalDocumentMapper.INSTANCE.fromIUpdateEmployeeRequestDto(dto, personalDocument);
        personalDocumentService.save(personalDocument);
        
        return new EmployeesResponseDto(
                user.getCompanyId(),
                user.getId(),
                user.getAvatar(),
                user.getEmail(),
               personalDocument.getAddress(),
                personalDocument.getAnnualSalary(),
                personalDocument.getDateOfBirth(),
                personalDocument.getDateOfEmployment(),
                personalDocument.getDateOfTermination(),
                personalDocument.getFirstName(),
                personalDocument.getLastName(),
                personalDocument.getGender().toString(),
                personalDocument.getIdentityNumber(),
                personalDocument.getSocialSecurityNumber(),
                personalDocument.getMobileNumber(),
                personalDocument.getPosition().toString(),
                personalDocument.getEmploymentStatus().toString()
                
                
               
                
        );
        
    }
}