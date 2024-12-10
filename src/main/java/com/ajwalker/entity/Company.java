package com.ajwalker.entity;

import com.ajwalker.utility.Enum.company.ECompanyType;
import com.ajwalker.utility.Enum.company.ERegion;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_company")
public class Company extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberShipPlanId;
    private String companyName;
    private String companyAddress;
    private String telNo;
    private String companyMail;
    private String companyLogo;
    @Enumerated(EnumType.STRING)
    private ECompanyType companyType;
    @Enumerated(EnumType.STRING)
    private ERegion region;
}
