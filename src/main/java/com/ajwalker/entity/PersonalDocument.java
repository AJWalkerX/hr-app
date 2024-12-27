package com.ajwalker.entity;

import com.ajwalker.utility.Enum.user.EEmploymentStatus;
import com.ajwalker.utility.Enum.user.EGender;
import com.ajwalker.utility.Enum.user.EPosition;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_personal_document")
public class PersonalDocument extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String identityNumber;
    private LocalDate dateOfBirth;
    private String mobileNumber;
    private String address;
    @Enumerated(EnumType.STRING)
    private EGender gender;
    private String email;
    @Enumerated(EnumType.STRING)
    private EPosition position;
    private LocalDate dateOfEmployment;
    private LocalDate dateOfTermination;
    private Double annualSalary;
    @Enumerated(EnumType.STRING)
    private EEmploymentStatus employmentStatus;
    private String socialSecurityNumber;

}