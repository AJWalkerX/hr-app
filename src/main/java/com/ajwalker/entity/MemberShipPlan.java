package com.ajwalker.entity;

import com.ajwalker.utility.Enum.memberShipPlan.EMemberShipState;
import com.ajwalker.utility.Enum.memberShipPlan.EMemberType;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_member_ship_plan")
public class MemberShipPlan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long companyId;
    private Double paymentAmount;
    private Long statDate;
    private Long endDate;
    @Enumerated(EnumType.STRING)
    private EMemberType memberType;
    @Enumerated(EnumType.STRING)
    private EMemberShipState memberShipState;
}
