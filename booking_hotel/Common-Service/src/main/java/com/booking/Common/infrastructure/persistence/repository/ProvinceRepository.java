package com.booking.Common.infrastructure.persistence.repository;

import com.booking.Common.infrastructure.persistence.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProvinceRepository extends JpaRepository<ProvinceEntity, UUID> {
    // tìm theo tên dưới dạng Like,không phân biết chữ hoa hay thường
    ProvinceEntity findByNameContainingIgnoreCase(String name);

}
