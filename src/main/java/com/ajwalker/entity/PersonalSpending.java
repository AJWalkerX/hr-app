package com.ajwalker.entity;

import com.ajwalker.utility.Enum.ESpendingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_personal_spending")
public class PersonalSpending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String description;
    private String billPdfUrl;
    private Double billAmount;
    private ESpendingType spendingType;
}
