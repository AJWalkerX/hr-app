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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final UserService userService;
    private final ShiftTrackingService shiftTrackingService;

    public Boolean createShift(List<CreateShiftRequestDto> dtoList, Long managerId) {
        Optional<User> userOptional = userService.findById(managerId);
        if (userOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
            User user = userOptional.get();
        if (dtoList.size() == 1) {
            CreateShiftRequestDto dto = dtoList.getFirst();
            Shift shift =  Shift.builder()
                    .shiftName(dto.shiftName())
                    .beginHour(dto.shiftStart())
                    .endHour(dto.shiftEnd())
                    .companyId(user.getCompanyId())
                    .build();
            shiftRepository.save(shift);
            return true;
        }
        else if (dtoList.size() > 1) {
            List<Shift> shiftList = new ArrayList<>();
            dtoList.forEach(dto -> {
                shiftList.add(Shift.builder()
                        .shiftName(dto.shiftName())
                        .beginHour(dto.shiftStart())
                        .endHour(dto.shiftEnd())
                        .companyId(user.getCompanyId())
                        .build());
            });
            shiftRepository.saveAll(shiftList);
            return true;
        }
        else{
            throw new HRAppException(ErrorType.INTERNAL_SERVER_ERROR);
        }

    }

    public Boolean assignShift(AssignShiftRequestDto dto) {
        return shiftTrackingService.assignShift(dto);

    }
}
