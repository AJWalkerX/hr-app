package com.ajwalker.controller;

import com.ajwalker.dto.request.*;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.EmployeesResponseDto;
import com.ajwalker.dto.response.ManagerSpendingResponseDto;
import com.ajwalker.dto.response.UserPermitResponseDto;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.service.ManagerService;
import com.ajwalker.utility.JwtManager;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(MANAGER)
@RequiredArgsConstructor
@CrossOrigin("*")
public class ManagerController {

    private final JwtManager jwtManager;
    private final ManagerService managerService;


    @GetMapping(MANAGER_EMPLOYEES)
    public ResponseEntity<BaseResponse<List<EmployeesResponseDto>>> employeeListByCompany(@RequestParam(name = "token") String token) {
        Optional<Long> optionalManagerId = jwtManager.verifyToken(token);
        if (optionalManagerId.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        return ResponseEntity.ok(BaseResponse.<List<EmployeesResponseDto>>builder()
                .message("Çalışan Listesi!!!")
                .code(200)
                .success(true)
                .data(managerService.employeeListByCompany(optionalManagerId.get()))
                .build());
    }
    
    @GetMapping(GETPERMITUSERLIST)
    public ResponseEntity<BaseResponse<List<UserPermitResponseDto>>> getUserPermitInfo(@RequestParam(name = "token") String token){
        if(jwtManager.verifyToken(token).isEmpty()){
            throw new HRAppException(ErrorType.INVALID_TOKEN);
        }
        Long managerId = jwtManager.verifyToken(token).get();
        return ResponseEntity.ok(BaseResponse.<List<UserPermitResponseDto>>builder()
                                             .message("kullanıcı bilgileri listelendi")
                                             .code(200)
                                             .success(true)
                                             .data(managerService.getUserPermitList(managerId))
                                             .build());
        
    }

    @PostMapping(PERMIT_AUTHORIZATION)
    public ResponseEntity<BaseResponse<Boolean>> authorizePermit(@RequestBody @Valid HolidayAuthorizeRequestDto dto) {
        if(jwtManager.verifyToken(dto.token()).isEmpty()){
            throw new HRAppException(ErrorType.INVALID_TOKEN);
        }
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .message("kullanıcı bilgileri listelendi")
                .code(200)
                .success(true)
                .data(managerService.authorizePermit(dto))
                .build());
    }
    
    @PutMapping(UPDATE_EMPLOYEE)
    public ResponseEntity<BaseResponse<Boolean>>updateEmployee(@RequestBody @Valid IUpdateEmployeeRequestDto dto){
        Optional<Long> managerIdOptional = jwtManager.verifyToken(dto.token());
        if (managerIdOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                                         .message("Çalışan bilgileri başarıyla güncellendi")
                                         .code(200)
                                         .success(true)
                                         .data(managerService.updateEmployee(dto))
                .build());
    }
    @DeleteMapping(DELETE_EMPLOYEE)
    public ResponseEntity<BaseResponse<Boolean>> deleteEmployee(@RequestBody DeleteEmployeeRequestDto dto){
        Optional<Long> managerId = jwtManager.verifyToken(dto.token());
        if (managerId.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        if (managerId.get().equals(dto.userId())){
            throw new HRAppException(ErrorType.DENIED_DELETE_USER);
        }
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .message("Çalışan bilgileri başarıyla silindi!")
                .code(200)
                .success(true)
                .data(managerService.deleteEmployee(dto.userId()))
                .build());
    }

    @PostMapping(ADD_NEW_EMPLOYEE)
    public ResponseEntity<BaseResponse<Boolean>> addEmployee(@RequestBody AddEmployeeRequestDto dto){
        System.out.println(dto.position());
        Optional<Long> managerIdOptional = jwtManager.verifyToken(dto.token());
        if (managerIdOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }

        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .message("Çalışan bilgileri başarıyla eklendi!")
                .code(200)
                .success(true)
                .data(managerService.addNewEmployee(dto, managerIdOptional.get()))
                .build());
    }
    
    @GetMapping(MANAGER_EMPLOYEES_SPENDING)
    public ResponseEntity<BaseResponse<List<ManagerSpendingResponseDto>>> employeeListBySpending(@RequestParam(name = "token") String token) {
        Optional<Long> optionalManagerId = jwtManager.verifyToken(token);
        if (optionalManagerId.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        return ResponseEntity.ok(BaseResponse.<List<ManagerSpendingResponseDto>>builder()
                                             .message("Çalışan Listesi!!!")
                                             .code(200)
                                             .success(true)
                                             .data(managerService.employeeListBySpending(optionalManagerId.get()))
                                             .build());
    }
    
    @PostMapping(SPENDING_AUTHORIZATION)
    public ResponseEntity<BaseResponse<Boolean>> authorizeSpending(@RequestBody @Valid SpendingAuthorizeRequestDto dto) {
        if(jwtManager.verifyToken(dto.token()).isEmpty()){
            throw new HRAppException(ErrorType.INVALID_TOKEN);
        }
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                                             .message("Spending bilgileri listelendi")
                                             .code(200)
                                             .success(true)
                                             .data(managerService.authorizeSpending(dto))
                                             .build());
    }
    
    @PutMapping(UPDATE_MANAGER)
    public ResponseEntity<BaseResponse<Boolean>> updateManager(@RequestBody @Valid UpdateManagerRequestDto dto){
        Optional<Long> optManagerId = jwtManager.verifyToken(dto.token());
        if (optManagerId.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                                         .message("manager bilgileri başarıyla kaydedildi")
                                         .success(true)
                                         .code(200)
                                             .data(managerService.updateManager(dto))
                                             .build());
    }
}