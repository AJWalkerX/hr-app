package com.ajwalker.utility.data;


import com.ajwalker.entity.Company;
import com.ajwalker.utility.Enum.company.ECompanyType;
import com.ajwalker.utility.Enum.company.ERegion;

import java.util.List;

public class CompanyGenerator {

    public static List<Company> generateCompanyList() {
        Company company = Company.builder()
                .region(ERegion.TURKEY)
                .companyType(ECompanyType.CONSTRUCTION)
                .companyMail("vivense@gmail.com")
                .companyLogo("https://cdn.prod.website-files.com/6113889e45c6e62ebf4ca212/61151b580dac64c995e2daea_kolayik_decathlon-1.svg")
                .companyName("VIVENSE")
                .telNo("333 999")
                .companyAddress("Sakli Koy")
                .build();
        Company company2 = Company.builder()
                .region(ERegion.TURKEY)
                .companyType(ECompanyType.FASHION_AND_TEXTILE)
                .companyMail("nike@gmail.com")
                .companyLogo("https://cdn.prod.website-files.com/6113889e45c6e62ebf4ca212/61151b72f1c2892d476ff297_nike-1.svg")
                .companyName("Nike")
                .telNo("333 999")
                .companyAddress("Sakli Koy")
                .build();
        Company company3 = Company.builder()
                .region(ERegion.TURKEY)
                .companyType(ECompanyType.AUTOMOTIVE)
                .companyMail("rent-go@gmail.com")
                .companyLogo("https://cdn.prod.website-files.com/6113889e45c6e62ebf4ca212/61152901d2468247ac4f0859_kolayik_rentgo.svg")
                .companyName("Rent Go")
                .telNo("333 999")
                .companyAddress("Sakli Koy")
                .build();
        Company company4 = Company.builder()
                .region(ERegion.TURKEY)
                .companyType(ECompanyType.TECHNOLOGY)
                .companyMail("kord.sa@gmail.com")
                .companyLogo("https://cdn.prod.website-files.com/6113889e45c6e62ebf4ca212/66f2c4c49bf5fbd4b7d904c3_kordsa.svg")
                .companyName("Kord SA")
                .telNo("333 999")
                .companyAddress("Sakli Koy")
                .build();
        Company company5 = Company.builder()
                .region(ERegion.TURKEY)
                .companyType(ECompanyType.HEALTHCARE)
                .companyMail("mac_gym@gmail.com")
                .companyLogo("https://cdn.prod.website-files.com/6113889e45c6e62ebf4ca212/672476df49dd3e0404491ce0_macfit%20yeni%20logo.avif")
                .companyName("MAC")
                .telNo("333 999")
                .companyAddress("Sakli Koy")
                .build();
        return List.of(company, company2, company3, company4, company5);
    }
}
