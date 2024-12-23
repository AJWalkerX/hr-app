package com.ajwalker.service;

import com.ajwalker.dto.request.CompanyCustomerInfoRequestDto;
import com.ajwalker.dto.response.CompanyCustomersInfoResponseDto;
import com.ajwalker.entity.Company;
import com.ajwalker.entity.MemberShipPlan;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.HRAppException;
import com.ajwalker.mapper.CompanyMapper;
import com.ajwalker.repository.CompanyRepository;
import com.ajwalker.utility.Enum.company.ECompanyState;
import com.ajwalker.utility.Enum.company.ECompanyType;
import com.ajwalker.utility.Enum.company.ERegion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final MemberShipPlanService memberShipPlanService;
    private final MemberShipTrackingService memberShipTrackingService;


    public Company createCompany(String companyName) {
        Optional<Company> companyOptional = getCompanyByName(companyName);
        if (companyOptional.isPresent()) {
            memberShipPlanService.createOrFindMemberShip(companyOptional.get().getId());
            return companyOptional.get();
        }

        Company company = Company.builder()
                .companyName(companyName)
                .companyType(ECompanyType.UNKNOWN)
                .region(ERegion.TURKEY)
                .companyState(ECompanyState.PENDING)
                .build();
        company = companyRepository.save(company);
        memberShipPlanService.createMemberShip(company.getId());
        return company;
    }

    public Optional<Company> getCompanyByName(String name) {
        return companyRepository.findByCompanyName(name);
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }
    
    
    public CompanyCustomersInfoResponseDto updateCompany(CompanyCustomerInfoRequestDto dto) {
        
        Optional<Company> companyOptional = companyRepository.findById(dto.companyId());
        if (companyOptional.isEmpty()) {
            throw new HRAppException(ErrorType.NOTFOUND_COMPANY);
            
        }
        Company company = companyOptional.get();
        company = CompanyMapper.INSTANCE.fromUpdateCompanyCustomerInfoRequestDto(dto,company);
        companyRepository.save(company);
       
        MemberShipPlan memberShipPlan =  memberShipPlanService.updateMemberShipPlan(dto.companyId(), dto.memberType());
        String totalPaymentAmount =
                memberShipTrackingService.findTotalPaymentByMemberShipPlanId(memberShipPlan.getId());
        return new CompanyCustomersInfoResponseDto(
                company.getId(),
                company.getCompanyLogo(),
                company.getCompanyName(),
                company.getCompanyMail(),
                company.getCompanyAddress(),
                company.getTelNo(),
                company.getCompanyType().toString(),
                company.getRegion().toString(),
                memberShipPlan.getMemberType().toString(),
                memberShipPlan.getMemberShipState().toString(),
                totalPaymentAmount
                
                
                
        );
        
        
    }

    public void updateCompanyInReview(Long companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setCompanyState(ECompanyState.IN_REVIEW);
            companyRepository.save(company);
        }
    }

    public Optional<Company> findById(Long companyId) {
        return companyRepository.findById(companyId);
    }

    public void updateCompanyToAccepted(Company company) {
        company.setCompanyState(ECompanyState.ACCEPTED);
    }

    public void updateCompanyToDenied(Company company) {
        company.setCompanyState(ECompanyState.DENIED);
    }


    public Map<Long,Company> getAllInReviewCompanies(List<Long> companyIds) {
        return companyRepository.findAllByIdAndStateInReview(companyIds, ECompanyState.IN_REVIEW)
                .stream().collect(Collectors.toMap(
                        Company::getId,
                        company -> company
                ));
    }

    public Optional<Company> findCompanyByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }
}