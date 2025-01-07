package com.ajwalker.repository;

import com.ajwalker.entity.Embezzlement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmbezzlementRepository extends JpaRepository<Embezzlement, Long> {
	
	List<Embezzlement> findEmbezzlementByCompanyId(Long companyId);
	
	
	
	
}