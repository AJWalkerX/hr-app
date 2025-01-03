package com.ajwalker.repository;

import com.ajwalker.entity.ShiftTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShiftTrackingRepository extends JpaRepository<ShiftTracking, Long> {
}
