package com.ajwalker.utility.data;


import com.ajwalker.entity.Company;
import com.ajwalker.utility.Enum.company.ECompanyType;
import com.ajwalker.utility.Enum.company.ERegion;

import java.util.List;

public class CompanyGenerator {

    public static List<Company> generateCompanyList() {
        Company company = Company.builder()
                .region(ERegion.TURKEY)
                .companyType(ECompanyType.AUTOMOTIVE)
                .companyMail("ahmetAmcaninYumurtalari@gmail.com")
                .companyLogo("https://picsum.photos/200/200")
                .companyName("Ahmet Amcanin Yumurtalari")
                .telNo("333 999")
                .companyAddress("Sakli Koy")
                .build();
        Company company2 = Company.builder()
                .region(ERegion.TURKEY)
                .companyType(ECompanyType.AUTOMOTIVE)
                .companyMail("ahmetAmcaninYumurtalari@gmail.com")
                .companyLogo("https://picsum.photos/200/200")
                .companyName("Ahmet Amcanin Yumurtalari")
                .telNo("333 999")
                .companyAddress("Sakli Koy")
                .build();
        Company company3 = Company.builder()
                .region(ERegion.TURKEY)
                .companyType(ECompanyType.AUTOMOTIVE)
                .companyMail("ahmetAmcaninYumurtalari@gmail.com")
                .companyLogo("https://picsum.photos/200/200")
                .companyName("Ahmet Amcanin Yumurtalari")
                .telNo("333 999")
                .companyAddress("Sakli Koy")
                .build();
        Company company4 = Company.builder()
                .region(ERegion.TURKEY)
                .companyType(ECompanyType.AUTOMOTIVE)
                .companyMail("ahmetAmcaninYumurtalari@gmail.com")
                .companyLogo("https://picsum.photos/200/200")
                .companyName("Ahmet Amcanin Yumurtalari")
                .telNo("333 999")
                .companyAddress("Sakli Koy")
                .build();
        Company company5 = Company.builder()
                .region(ERegion.TURKEY)
                .companyType(ECompanyType.AUTOMOTIVE)
                .companyMail("ahmetAmcaninYumurtalari@gmail.com")
                .companyLogo("https://picsum.photos/200/200")
                .companyName("Ahmet Amcanin Yumurtalari")
                .telNo("333 999")
                .companyAddress("Sakli Koy")
                .build();
        return List.of(company, company2, company3, company4, company5);
    }
}
