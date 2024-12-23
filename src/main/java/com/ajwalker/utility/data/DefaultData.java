package com.ajwalker.utility.data;

import com.ajwalker.entity.*;
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
    private final CommentRepository commentRepository;
    private final MemberShipTrackingRepository memberShipTrackingRepository;
    
    
    @PostConstruct
    public void init() {
        if(adminRepository.count() == 0) {
            generateAdmin();
        }
        if(commentRepository.count() == 0) {
            companyRepository.saveAll(CommentGenerator.createCompany());
            userRepository.saveAll(CommentGenerator.createUser());
            commentRepository.saveAll(CommentGenerator.createComment());
            personalDocumentRepository.saveAll(CommentGenerator.createPersonalDocument());
        }
        if(memberShipPlanRepository.count() == 0) {
            memberShipPlanRepository.saveAll(MemberShipPlanGenerator.generateMemberShipPlans());
        }
        if(memberShipTrackingRepository.count() == 0) {
            memberShipTrackingRepository.saveAll(MemberShipPlanGenerator.generateMemberShipTracking());
        }

        if(companyRepository.count() == 5) {
            companyRepository.saveAll(CompanyGenerator.generateCompanyList());

        }
        if(userRepository.count() == 5) {
            userRepository.saveAll(UserGenerator.generateUser());
        }
        if(personalDocumentRepository.count() == 5) {
            personalDocumentRepository.saveAll(PersonalDocumentGenerator.generatePersonalDocuments());
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