package com.ajwalker.controller;

import com.ajwalker.dto.request.AssignShiftRequestDto;
import com.ajwalker.dto.request.CreateShiftRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.service.ShiftService;
import com.ajwalker.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping(SHIFT)
public class ShiftController {
    private final JwtManager jwtManager;
    private final ShiftService shiftService;
    @PostMapping(CREATE_SHIFT)
    public ResponseEntity<BaseResponse<Boolean>> createShift(@RequestBody @Valid CreateShiftRequestDto dto) {
        Optional<Long> managerIdOptional = jwtManager.verifyToken(dto.token());
        if (managerIdOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .success(true)
                        .data(shiftService.createShift(dto, managerIdOptional.get()))
                        .code(200)
                        .message("Vardiya olusturma islemi tamamlandi!")
                .build());
    }

    @PostMapping(ASSIGN_SHIFT)
    public ResponseEntity<BaseResponse<Boolean>> assignShift(@RequestBody @Valid AssignShiftRequestDto dto){
        Optional<Long> managerIdOptional = jwtManager.verifyToken(dto.token());
        if (managerIdOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Vardiya atama islemi basarili bir sekilde olusturuldu!")
                        .data(shiftService.assignShift(dto))
                        .success(true)
                .build());
    }
}
