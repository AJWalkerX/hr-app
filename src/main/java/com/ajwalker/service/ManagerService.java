package com.ajwalker.service;

import com.ajwalker.dto.request.HolidayAuthorizeRequestDto;
import com.ajwalker.dto.response.EmployeesResponseDto;
import com.ajwalker.dto.response.UserPermitResponseDto;
import com.ajwalker.entity.Company;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.User;
import com.ajwalker.entity.WorkHoliday;
import com.ajwalker.repository.CompanyRepository;
import com.ajwalker.utility.Enum.holiday.EHolidayState;
import lombok.RequiredArgsConstructor;
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


    public List<EmployeesResponseDto> employeeListByCompany(Long companyId) {
        List<User> users = userService.findUserInfo(companyId);
        List<EmployeesResponseDto> employeeList = new ArrayList<>();

        for (User user : users) {
            PersonalDocument personalDocuments = personalDocumentService.findByUserIdInfo(user.getId());

            employeeList.add(new EmployeesResponseDto(
                    user.getCompanyId(),
                    user.getId(),
                    user.getAvatar() != null ? user.getAvatar() : "",
                    user.getEmail() != null ? user.getEmail() : "",
                    personalDocuments != null && personalDocuments.getAddress() != null ? personalDocuments.getAddress() : "",
                    personalDocuments != null && personalDocuments.getAnnualSalary() != null ? personalDocuments.getAnnualSalary() : 0.0,
                    personalDocuments != null && personalDocuments.getDateOfBirth() != null ? personalDocuments.getDateOfBirth() : null,
                    personalDocuments != null && personalDocuments.getDateOfEmployment() != null ? personalDocuments.getDateOfEmployment() : null,
                    personalDocuments != null && personalDocuments.getDateOfTermination() != null ? personalDocuments.getDateOfTermination() : null,
                    personalDocuments != null && personalDocuments.getFirstName() != null ? personalDocuments.getFirstName() : "",
                    personalDocuments != null && personalDocuments.getLastName() != null ? personalDocuments.getLastName() : "",
                    personalDocuments != null && personalDocuments.getGender() != null ? personalDocuments.getGender().toString() : "",
                    personalDocuments != null && personalDocuments.getIdentityNumber() != null ? personalDocuments.getIdentityNumber() : "",
                    personalDocuments != null && personalDocuments.getSocialSecurityNumber() != null ? personalDocuments.getSocialSecurityNumber() : "",
                    personalDocuments != null && personalDocuments.getMobileNumber() != null ? personalDocuments.getMobileNumber() : "",
                    personalDocuments != null && personalDocuments.getPosition() != null ? personalDocuments.getPosition().toString() : "",
                    personalDocuments != null && personalDocuments.getEmploymentStatus() != null ? personalDocuments.getEmploymentStatus().toString() : ""
            ));
        }

        return employeeList;
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
}