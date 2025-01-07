package com.ajwalker.controller;

import com.ajwalker.dto.request.AssignShiftRequestDto;
import com.ajwalker.dto.request.CreateShiftRequestDto;
import com.ajwalker.dto.request.DeleteShiftRequestDto;
import com.ajwalker.dto.request.UpdateShiftRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.dto.response.MyShiftResponseDto;
import com.ajwalker.dto.response.ShiftResponseDto;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.service.ShiftService;
import com.ajwalker.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<BaseResponse<Boolean>> createShift(@RequestBody @Valid List<CreateShiftRequestDto> dto) {
        Optional<Long> managerIdOptional = jwtManager.verifyToken(dto.getFirst().token());
        if (managerIdOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .success(true)
                        .data(shiftService.createAndCheckShift(dto, managerIdOptional.get()))
                        .code(200)
                        .message("Vardiya olusturma islemi tamamlandi!")
                .build());
    }

    @PostMapping(ASSIGN_SHIFT)
    public ResponseEntity<BaseResponse<Boolean>> assignShift(@RequestBody @Valid List<AssignShiftRequestDto> dto){
        Optional<Long> managerIdOptional = jwtManager.verifyToken(dto.getFirst().token());
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

    @GetMapping(LIST_SHIFT)
    public ResponseEntity<BaseResponse<List<ShiftResponseDto>>> getAllCompanyShifts(@RequestParam(name = "token") String token){
        Optional<Long> managerId = jwtManager.verifyToken(token);
        if (managerId.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        return ResponseEntity.ok(BaseResponse.<List<ShiftResponseDto>>builder()
                .code(200)
                .message("Sirkete ait tum vardiyalar getirildi!")
                .data(shiftService.getAllCompanyShifts(managerId.get()))
                .success(true)
                .build());
    }

    @GetMapping(MY_SHIFTS)
    public ResponseEntity<BaseResponse<List<MyShiftResponseDto>>> getAllMyShifts(@RequestParam(name = "token") String token){
        Optional<Long> optUserId = jwtManager.verifyToken(token);
        if (optUserId.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_USER);
        }
        Long userId = optUserId.get();
        return ResponseEntity.ok(BaseResponse.<List<MyShiftResponseDto>>builder()
                        .success(true)
                        .code(200)
                        .message("Calisan vardiyalari basarili bir sekilde getirildi!")
                        .data(shiftService.getAllMyShifts(userId))
                .build());
    }

    @DeleteMapping(DELETE_SHIFT)
    public ResponseEntity<BaseResponse<Boolean>> deleteShift(@RequestBody DeleteShiftRequestDto dto){
        Optional<Long> optUserId = jwtManager.verifyToken(dto.token());
        if (optUserId.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .success(true)
                .code(200)
                .message("Vardiya basatili bir sekilde guncelledi!")
                .data(shiftService.deleteShift(dto.shiftId()))
                .build());
    }
}
