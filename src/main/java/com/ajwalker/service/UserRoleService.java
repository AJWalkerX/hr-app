package com.ajwalker.service;

import com.ajwalker.entity.UserRole;
import com.ajwalker.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public List<UserRole> findAllByUserId(Long userId) {
        return userRoleRepository.findAllByUserId(userId);
    }
}
