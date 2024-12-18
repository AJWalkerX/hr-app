package com.ajwalker.service;

import com.ajwalker.entity.Company;
import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.repository.CompanyRepository;
import com.ajwalker.utility.Enum.user.EUserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final MemberShipPlanService memberShipPlanService;

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company createCompany(String companyName) {
        Optional<Company> companyOptional = getCompanyByName(companyName);
        if (companyOptional.isPresent()) {
            return companyOptional.get();
        }

        Company company = Company.builder().companyName(companyName).build();
        company = companyRepository.save(company);
        memberShipPlanService.createMemberShip(company.getId());
        return company;
    }

    public Optional<Company> getCompanyByName(String name) {
        return companyRepository.findByCompanyName(name);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
