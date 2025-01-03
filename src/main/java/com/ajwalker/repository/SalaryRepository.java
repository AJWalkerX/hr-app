package com.ajwalker.repository;

import com.ajwalker.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
	
	List<Salary> findByUserId(Long userId);
	
	Optional<Salary> findByUserIdAndSalaryDate(Long userId, LocalDate nextMonth);
	
}