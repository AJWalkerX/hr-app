package com.ajwalker.entity;

import com.ajwalker.utility.Enum.user.EUserRole;
import com.ajwalker.utility.Enum.user.EUserState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long companyId;
    private String email;
    private String password;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private EUserRole userRole;
    @Enumerated(EnumType.STRING)
    private EUserState userState;
}
