package com.ajwalker.entity;

import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private Long userId;
    @Column(unique = true, nullable = false)
    private Long companyId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private Long commentDate;
}
