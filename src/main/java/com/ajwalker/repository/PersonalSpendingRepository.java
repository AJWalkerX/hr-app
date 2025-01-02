package com.ajwalker.repository;

import com.ajwalker.entity.PersonalSpending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalSpendingRepository extends JpaRepository<PersonalSpending, Long> {
	
	
	List<PersonalSpending> findAllByUserId(Long personalId);
	
}