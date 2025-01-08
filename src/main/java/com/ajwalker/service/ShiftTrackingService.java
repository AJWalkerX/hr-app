package com.ajwalker.service;

import com.ajwalker.dto.request.AssignShiftRequestDto;
import com.ajwalker.entity.ShiftTracking;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.ShiftTrackingRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftTrackingService {
    private final ShiftTrackingRepository shiftTrackingRepository;
    private final UserService userService;

    public Boolean checkBeforeAssignShift(@Valid List<AssignShiftRequestDto> dto) {
        dto.forEach( d -> {
            Long userId = userService.findUserForShift(d.firstName(), d.lastName(), d.email());
            List<ShiftTracking> shiftTrackingList = shiftTrackingRepository.findAllByUserId(userId);
            if (!shiftTrackingList.isEmpty()) {
                shiftTrackingList.forEach(shiftTracking -> {
                    if (shiftTracking.getBeginDate().isBefore(d.endDate())) {
                        throw new HRAppException(ErrorType.INVALID_SHIFT_TIMING);
                    }
                });
            }
             assignShift(userId, d.startDate(), d.endDate(), d.shiftId());
        });
        return true;
    }

    public Boolean assignShift(Long userId, LocalDate startDate, LocalDate endDate, Long shiftId) {

        ShiftTracking shiftTracking = ShiftTracking.builder()
                .userId(userId)
                .beginDate(startDate)
                .endDate(endDate)
                .shiftId(shiftId)
                .build();
        shiftTrackingRepository.save(shiftTracking);
        return true;
    }

    public List< ShiftTracking> getAllMyShiftTracking(Long userId) {
        return shiftTrackingRepository.findAllByUserId(userId);

    }
}
