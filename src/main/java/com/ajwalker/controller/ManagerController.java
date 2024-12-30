package com.ajwalker.controller;

import com.ajwalker.dto.request.AddEmployeeRequestDto;
import com.ajwalker.dto.request.HolidayAuthorizeRequestDto;
import com.ajwalker.dto.request.IUpdateEmployeeRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.EmployeesResponseDto;
import com.ajwalker.dto.response.UserPermitResponseDto;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.service.ManagerService;
import com.ajwalker.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<BaseResponse<List<UserPermitResponseDto>>> getUserPermitInfo(){
        return ResponseEntity.ok(BaseResponse.<List<UserPermitResponseDto>>builder()
                                             .message("kullanıcı bilgileri listelendi")
                                             .code(200)
                                             .success(true)
                                             .data(managerService.getUserPermitList())
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
    @DeleteMapping
    public ResponseEntity<BaseResponse<Boolean>> deleteEmployee(@RequestBody Long userId, String token){
        Optional<Long> managerId = jwtManager.verifyToken(token);
        if (managerId.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .message("Çalışan bilgileri başarıyla güncellendi")
                .code(200)
                .success(true)
                .data(managerService.deleteEmployee(userId))
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
}