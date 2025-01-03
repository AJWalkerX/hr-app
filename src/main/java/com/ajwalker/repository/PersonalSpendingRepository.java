package com.ajwalker.repository;

import com.ajwalker.entity.PersonalSpending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonalSpendingRepository extends JpaRepository<PersonalSpending, Long> {
	
	
	List<PersonalSpending> findAllByUserId(Long personalId);
	
	@Query("SELECT P FROM PersonalSpending P WHERE P.userId IN(?1)")
	List<PersonalSpending> findAllByUserIds(List<Long> employeeIds);
	
}