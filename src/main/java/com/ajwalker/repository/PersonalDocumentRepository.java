package com.ajwalker.repository;

import com.ajwalker.entity.PersonalDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDocumentRepository extends JpaRepository<PersonalDocument, Long> {
}
