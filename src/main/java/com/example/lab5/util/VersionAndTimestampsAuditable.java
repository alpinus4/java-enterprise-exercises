package com.example.lab5.util;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class VersionAndTimestampsAuditable {

    @Version
    private Long version;

    @Column(name = "created_at_date_time")
    private LocalDateTime createdAtDateTime;

    @Column(name = "updated_at_date_time")
    private LocalDateTime updatedAtDateTime;

    @PrePersist
    public void updateCreatedAtDateTime() {
        createdAtDateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void updateUpdatedAtDateTime() {
        updatedAtDateTime = LocalDateTime.now();
    }

}
