package com.ajwalker.repository;

import com.ajwalker.entity.WorkHoliday;
import com.ajwalker.utility.Enum.holiday.EHolidayState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkHolidayRepository extends JpaRepository<WorkHoliday, Long> {
    @Query("SELECT W FROM WorkHoliday W WHERE W.holidayState = ?1")
    List<WorkHoliday> findAllWorkHolidaysInPending(EHolidayState eHolidayState);
    List<WorkHoliday> findByUserId(Long userId);
}