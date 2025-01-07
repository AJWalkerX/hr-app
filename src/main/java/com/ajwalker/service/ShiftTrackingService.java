package com.ajwalker.service;

import com.ajwalker.dto.request.AssignShiftRequestDto;
import com.ajwalker.entity.ShiftTracking;
import com.ajwalker.repository.ShiftTrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftTrackingService {
    private final ShiftTrackingRepository shiftTrackingRepository;
    private final UserService userService;

    public Boolean assignShift(AssignShiftRequestDto dto) {
        Long userId = userService.findUserForShift(dto.firstName(), dto.lastName(), dto.email());
        ShiftTracking shiftTracking = ShiftTracking.builder()
                .userId(userId)
                .beginDate(dto.startDate())
                .endDate(dto.endDate())
                .build();
        shiftTrackingRepository.save(shiftTracking);
        return true;
    }

    public List< ShiftTracking> getAllMyShiftTracking(Long userId) {
        return shiftTrackingRepository.findAllByUserId(userId);

    }
}
