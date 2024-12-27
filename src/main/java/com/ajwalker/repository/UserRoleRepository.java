package com.ajwalker.repository;

import com.ajwalker.entity.UserRole;
import com.ajwalker.utility.Enum.user.EUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByUserId(Long userId);
    
    Optional<UserRole> findByUserIdAndRole(Long userId, EUserRole role);
}