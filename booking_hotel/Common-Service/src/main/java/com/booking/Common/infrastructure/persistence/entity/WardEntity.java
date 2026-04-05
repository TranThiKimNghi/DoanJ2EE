package com.booking.Common.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ward")
@EqualsAndHashCode(callSuper = true)
public class WardEntity extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "province_id", nullable = false)
    private UUID provinceId;

}
