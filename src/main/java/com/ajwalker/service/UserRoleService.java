package com.ajwalker.service;

import com.ajwalker.entity.UserRole;
import com.ajwalker.repository.UserRoleRepository;
import com.ajwalker.utility.Enum.user.EUserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public List<UserRole> findAllByUserId(Long userId) {
        return userRoleRepository.findAllByUserId(userId);
    }
    
    public Optional<UserRole> findByUserIdAndRole(Long userId, EUserRole role) {
        return userRoleRepository.findByUserIdAndRole(userId, role);
    }
    
}