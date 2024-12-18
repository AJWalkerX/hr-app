package com.ajwalker.entity;

import com.ajwalker.utility.Enum.memberShipPlan.EMemberShipState;
import com.ajwalker.utility.Enum.memberShipPlan.EMemberType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_member_ship_plan")
public class MemberShipPlan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long companyId;
    @Enumerated(EnumType.STRING)
    private EMemberType memberType;
    @Enumerated(EnumType.STRING)
    private EMemberShipState memberShipState;
}
//TODO MemberShipPlan ve MemberShipTracking