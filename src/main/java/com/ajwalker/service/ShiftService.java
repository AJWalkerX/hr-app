package com.ajwalker.service;

import com.ajwalker.dto.request.AssignShiftRequestDto;
import com.ajwalker.dto.request.CreateShiftRequestDto;
import com.ajwalker.dto.request.UpdateShiftRequestDto;
import com.ajwalker.dto.response.MyShiftResponseDto;
import com.ajwalker.dto.response.ShiftResponseDto;
import com.ajwalker.entity.Shift;
import com.ajwalker.entity.ShiftTracking;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.repository.ShiftRepository;
import com.ajwalker.repository.ShiftTrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final UserService userService;
    private final ShiftTrackingService shiftTrackingService;
    private final ShiftTrackingRepository shiftTrackingRepository;

    public Boolean createAndCheckShift(List<CreateShiftRequestDto> dtoList, Long managerId) {
        Optional<User> userOptional = userService.findById(managerId);
        if (userOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
            User user = userOptional.get();
        if(shiftRepository.count() == 0) {
            return createShift(dtoList, user);
        }
        else {
            List<Shift> allShifts = shiftRepository.findAll();
            AtomicLong totalMinutes = new AtomicLong(allShifts.stream()
                    .mapToLong(shift -> {
                        long duration = Duration.between(shift.getBeginHour(), shift.getEndHour()).toMinutes();
                        if (duration < 0) {
                            duration += 24 * 60;
                        }
                        return duration;
                    })
                    .sum());

            dtoList.forEach(dto -> {
                long duration = Duration.between(dto.shiftStart(), dto.shiftEnd()).toMinutes();
                if (duration < 0) {
                    duration += 24 * 60;
                }

                if (totalMinutes.get() + duration > 24 * 60) {
                    throw new HRAppException(ErrorType.OUTOFBOUNDRY_SHIFT_HOURS);
                }

                totalMinutes.addAndGet(duration);
            });

            if (totalMinutes.get() < 24 * 60) {
                return createShift(dtoList, user);
            }
        }
        return false;
    }

    private Boolean createShift(List<CreateShiftRequestDto> dtoList, User user) {
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

    public List<ShiftResponseDto> getAllCompanyShifts(Long managerId) {
        Optional<User> userOptional = userService.findById(managerId);
        if (userOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_MANAGER);
        }
        User user = userOptional.get();

        List<Shift> shiftList = shiftRepository.findAllByCompanyId(user.getCompanyId());
        return shiftList.stream()
                .map(shift -> new ShiftResponseDto(
                        shift.getId(),
                        shift.getShiftName(),
                        shift.getBeginHour(),
                        shift.getEndHour()
                ))
                .collect(Collectors.toList());
    }

    public List<MyShiftResponseDto> getAllMyShifts(Long userId) {
        List<ShiftTracking> allMyShiftTracking = shiftTrackingService.getAllMyShiftTracking(userId);
        List<Long> shiftIdList = allMyShiftTracking.stream().map(ShiftTracking::getShiftId).toList();
        Map<Long, Shift> shiftMap = shiftRepository.findAllByIds(shiftIdList)
                .stream()
                .collect(Collectors.toMap(
                        Shift::getId,
                        shift -> shift
                ));
        return allMyShiftTracking.stream().map(shiftTracking -> {
            Shift shift = shiftMap.get(shiftTracking.getShiftId());
            return new MyShiftResponseDto(
                    shift.getShiftName(),
                    shift.getBeginHour(),
                    shift.getEndHour(),
                    shiftTracking.getBeginDate(),
                    shiftTracking.getEndDate()
            );
                }
        ).collect(Collectors.toList());
    }

    public Boolean updateShift(UpdateShiftRequestDto dto) {
        Optional<Shift> shiftOptional = shiftRepository.findById(dto.shiftId());
        if (shiftOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_SHIFT);
        }
        Shift shift = shiftOptional.get();
        shift.setShiftName(dto.shiftName() != null ? dto.shiftName() : shift.getShiftName());
        shift.setBeginHour(dto.shiftStart() != null ? dto.shiftStart() : shift.getBeginHour());
        shift.setEndHour(dto.shiftEnd() != null ? dto.shiftEnd() : shift.getEndHour());
        shiftRepository.save(shift);
        return true;
    }

    public Boolean deleteShift(Long shiftId) {
        shiftRepository.deleteById(shiftId);
        return true;
    }
}
