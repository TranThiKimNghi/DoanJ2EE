package com.booking.Common.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass

public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator =  "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "created_by", updatable = false)
    private UUID createdBy;

    @Column(name = "updated_at")
    private  LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "updated_by")
    private UUID updatedBy;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

}
