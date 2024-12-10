package com.ajwalker.entity;


import com.ajwalker.utility.Enum.embezzlement.EEmbezzlementState;
import com.ajwalker.utility.Enum.embezzlement.EEmbezzlementType;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_embezzlement")
public class Embezzlement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private EEmbezzlementType embezzlementType;
    @Enumerated(EnumType.STRING)
    private EEmbezzlementState embezzlementState;
}
