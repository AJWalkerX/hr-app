package com.ajwalker.repository;

import com.ajwalker.entity.Company;
import com.ajwalker.entity.User;
import com.ajwalker.utility.Enum.company.ECompanyState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByCompanyName(String name);

    @Query("SELECT C from Company C WHERE C.companyState = ?2 AND C.id IN (?1) ")
    List<Company> findAllByIdAndStateInReview(List<Long> companyIds, ECompanyState inReview);

    List<Company> findAllByCompanyState(ECompanyState eCompanyState);
	
	
}