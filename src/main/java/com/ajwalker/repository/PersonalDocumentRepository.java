package com.ajwalker.repository;

import com.ajwalker.entity.PersonalDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalDocumentRepository extends JpaRepository<PersonalDocument, Long> {
	Optional<PersonalDocument> findByUserId(Long userId);
	
}