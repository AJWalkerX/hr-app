package com.ajwalker.entity;


import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_user_monthly_salary")
public class UserMonthlySalary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long monthlySalaryAmount;
    private Long totalMonthlySalary;
    private Long bonusAmount;
    private Long paymentDate;
}
