package com.ajwalker.repository;

import com.ajwalker.entity.Admin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	Optional<Admin> findOptionalByUsernameAndPassword(String username, String password);
}