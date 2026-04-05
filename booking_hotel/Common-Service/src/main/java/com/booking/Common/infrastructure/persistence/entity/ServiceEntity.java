package com.booking.Common.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "services")
@EqualsAndHashCode(callSuper = true)
public class ServiceEntity extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

}
