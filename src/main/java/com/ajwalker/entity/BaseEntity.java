package com.ajwalker.entity;

import com.ajwalker.utility.Enum.EState;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@MappedSuperclass
public class BaseEntity {
    private Long create_at;
    private Long update_at;
    private EState state;

    @PrePersist
    protected void prePersist() {
        this.update_at = System.currentTimeMillis();
        this.create_at = System.currentTimeMillis();
        this.state = EState.ACTIVE;
    }
    @PreUpdate
    protected void preUpdate() {
        this.update_at = System.currentTimeMillis();
    }

}
