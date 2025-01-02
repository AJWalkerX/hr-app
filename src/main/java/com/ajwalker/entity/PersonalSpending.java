package com.ajwalker.entity;

import com.ajwalker.utility.Enum.personalSpending.ESpendingState;
import com.ajwalker.utility.Enum.personalSpending.ESpendingType;
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
@Table(name = "tbl_personal_spending")
public class PersonalSpending extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String description;
    private String billPdfUrl;
    private Double billAmount;
    private LocalDate spendingDate;
    @Enumerated(EnumType.STRING)
    private ESpendingState spendingState;
    @Enumerated(EnumType.STRING)
    private ESpendingType spendingType;
}