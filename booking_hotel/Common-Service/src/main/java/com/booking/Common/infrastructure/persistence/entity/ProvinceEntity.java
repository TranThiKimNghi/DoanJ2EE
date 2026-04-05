package com.booking.Common.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "province")
@EqualsAndHashCode(callSuper = true)
public class ProvinceEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

}
