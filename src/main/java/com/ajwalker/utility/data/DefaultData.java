package com.ajwalker.utility.data;

import com.ajwalker.entity.Admin;
import com.ajwalker.entity.AdminRole;
import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.repository.*;
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
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final MemberShipPlanRepository memberShipPlanRepository;
    private final PersonalDocumentRepository personalDocumentRepository;
    
    
    @PostConstruct
    public void init() {
        if(adminRepository.count() == 0) {
            generateAdmin();
        }
        if(companyRepository.count() == 0) {
            CompanyGenerator.generateCompanyList();
        }
        if(userRepository.count() == 0) {
            UserGenerator.generateUser();
        }
        if(memberShipPlanRepository.count() == 0) {
            MemberShipPlanGenerator.generateMemberShipPlans();
        }
        if(personalDocumentRepository.count() == 0) {
            PersonalDocumentGenerator.generatePersonalDocuments();
        }
    }

    public void generateAdmin(){
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