package com.ajwalker.utility;

import com.ajwalker.entity.Admin;
import com.ajwalker.entity.AdminRole;
import com.ajwalker.repository.AdminRepository;
import com.ajwalker.repository.AdminRoleRepository;
import com.ajwalker.utility.Enum.EAdminRole;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultData {
    private final AdminRepository adminRepository;
    private final AdminRoleRepository adminRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if(adminRepository.count() == 0) {
            Admin admin = Admin.builder()
                    .username("main_admin")
                    .password(passwordEncoder.encode("admin_root123"))
                    .build();
           admin = adminRepository.save(admin);
           if(adminRoleRepository.findAllByAdminId(admin.getId()).isEmpty()) {
               AdminRole adminRole = AdminRole.builder()
                       .adminId(admin.getId())
                       .adminRole(EAdminRole.ADMIN)
                       .build();
               adminRoleRepository.save(adminRole);
           }
        }
    }
}
