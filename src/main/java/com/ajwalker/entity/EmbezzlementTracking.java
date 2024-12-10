package com.ajwalker.entity;


import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_embezzlement_tracking")
public class EmbezzlementTracking extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long embezzlementId;
    private Long userId;
    private Long givenDate;
    private Long takenDate;
}
