package com.ajwalker.service;

import com.ajwalker.dto.request.AssignShiftRequestDto;
import com.ajwalker.entity.ShiftTracking;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.ShiftTrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftTrackingService {
    private final ShiftTrackingRepository shiftTrackingRepository;
    private final UserService userService;

    public Boolean checkBeforeAssignShift(AssignShiftRequestDto dto) {
        Long userId = userService.findUserForShift(dto.firstName(), dto.lastName(), dto.email());
        List<ShiftTracking> shiftTrackingList = shiftTrackingRepository.findAllByUserId(userId);
            if (!shiftTrackingList.isEmpty()) {
                shiftTrackingList.forEach(shiftTracking -> {
                    if (shiftTracking.getEndDate().isBefore(dto.startDate()) || dto.startDate().isBefore(shiftTracking.getBeginDate())) {
                        throw new HRAppException(ErrorType.INVALID_SHIFT_TIMING);
                    }
                });
            }
                return assignShift(userId, dto.startDate(), dto.endDate());
    }

    public Boolean assignShift(Long userId, LocalDate startDate, LocalDate endDate) {

        ShiftTracking shiftTracking = ShiftTracking.builder()
                .userId(userId)
                .beginDate(startDate)
                .endDate(endDate)
                .build();
        shiftTrackingRepository.save(shiftTracking);
        return true;
    }

    public List< ShiftTracking> getAllMyShiftTracking(Long userId) {
        return shiftTrackingRepository.findAllByUserId(userId);

    }
}
