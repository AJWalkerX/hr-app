package com.ajwalker.service;

import com.ajwalker.dto.request.HolidayAuthorizeRequestDto;
import com.ajwalker.dto.request.IUpdateEmployeeRequestDto;
import com.ajwalker.dto.response.EmployeesResponseDto;
import com.ajwalker.dto.response.UserPermitResponseDto;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.mapper.PersonalDocumentMapper;
import com.ajwalker.repository.PersonalDocumentRepository;
import com.ajwalker.utility.Enum.holiday.EHolidayState;
import lombok.RequiredArgsConstructor;
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
                    personalDocument.getGender().toString(),
                    personalDocument.getIdentityNumber(),
                    personalDocument.getSocialSecurityNumber(),
                    personalDocument.getMobileNumber(),
                    personalDocument.getPosition().toString(),
                    personalDocument.getEmploymentStatus().toString()
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
    
    public EmployeesResponseDto updateEmployee(IUpdateEmployeeRequestDto dto) {
        Optional<User> optionalUser = userService.findUserById(dto.userId());
        if (optionalUser.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_USER);
        }
        User user = optionalUser.get();
 
        
        PersonalDocument personalDocument = personalDocumentService.findPersonalByUserId(dto.userId());
        
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