package com.ajwalker.service;

import com.ajwalker.entity.Company;
import com.ajwalker.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;


    public Company createCompany(String companyName) {
        Company company = Company.builder().companyName(companyName).build();
        return companyRepository.save(company);
    }
}
