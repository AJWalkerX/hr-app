package com.ajwalker.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_embezzlement_tracking")
public class EmbezzlementTracking extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long embezzlementId;
    private Long managerId;
    private Long userId;
    private Long givenDate;
    private Long takenDate;
}