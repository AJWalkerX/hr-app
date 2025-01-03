package com.ajwalker.service;

import com.ajwalker.dto.request.AssignShiftRequestDto;
import com.ajwalker.dto.request.CreateShiftRequestDto;
import com.ajwalker.entity.Shift;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final UserService userService;
    private final ShiftTrackingService shiftTrackingService;
    public Boolean createShift(CreateShiftRequestDto dto, Long managerId) {
        Optional<User> userOptional = userService.findById(managerId);
        if (userOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        User user = userOptional.get();
        Shift shift =  Shift.builder()
                .shiftName(dto.shiftName())
                .beginHour(dto.shiftStart())
                .endHour(dto.shiftEnd())
                .companyId(user.getCompanyId())
                .build();
        shiftRepository.save(shift);
        return true;
    }

    public Boolean assignShift(AssignShiftRequestDto dto) {
        return shiftTrackingService.assignShift(dto);

    }
}
