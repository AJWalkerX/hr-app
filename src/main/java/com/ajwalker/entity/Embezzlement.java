package com.ajwalker.entity;


import com.ajwalker.utility.Enum.embezzlement.EEmbezzlementState;
import com.ajwalker.utility.Enum.embezzlement.EEmbezzlementType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_embezzlement")
public class Embezzlement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Long companyId;
    @Enumerated(EnumType.STRING)
    private EEmbezzlementType embezzlementType;
    @Enumerated(EnumType.STRING)
    private EEmbezzlementState embezzlementState;
}