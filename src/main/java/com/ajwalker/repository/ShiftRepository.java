package com.ajwalker.repository;

import com.ajwalker.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

    List<Shift> findAllByCompanyId(Long companyId);

    @Query("SELECT S FROM Shift S WHERE S.id IN(?1)")
    List<Shift> findAllByIds(List<Long> shiftIdList);
}
