package com.ajwalker.repository;

import com.ajwalker.entity.PersonalSpending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalSpendingRepository extends JpaRepository<PersonalSpending, Long> {

}