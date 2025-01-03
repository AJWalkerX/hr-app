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
    public Boolean assignShift(AssignShiftRequestDto dto) {
         List<ShiftTracking> shiftTrackings = new ArrayList<>();
        dto.userId().forEach(userId -> {
            new ShiftTracking();
            shiftTrackings.add(ShiftTracking.builder()
                            .shiftId(dto.shiftId())
                            .userId(userId)
                            .beginDate(dto.startDate())
                            .endDate(dto.endDate())
                    .build());
        });
        shiftTrackingRepository.saveAll(shiftTrackings);
        return true;
    }
}
