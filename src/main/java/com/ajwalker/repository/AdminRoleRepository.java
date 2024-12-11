package com.ajwalker.repository;

import com.ajwalker.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AdminRoleRepository extends JpaRepository<AdminRole, Long> {
    List<AdminRole> findAllByAdminId(Long id);
}
