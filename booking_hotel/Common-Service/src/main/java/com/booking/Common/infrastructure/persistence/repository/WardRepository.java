package com.booking.Common.infrastructure.persistence.repository;

import com.booking.Common.infrastructure.persistence.entity.WardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WardRepository extends JpaRepository<WardEntity, UUID> {
    // Lấy all phường trong 1 tỉnh  có sắp xếp tăng dần theo tên
    List<WardEntity> findByProvinceIdOrderByNameAsc(UUID provinceId);

    //Tìm kiếmm 1 phường trong 1 tỉnh chỉ cần gõ 1 phần của tên
    List<WardEntity> findByProvinceIdAndNameContainingIgnoreCaseOrderByNameAsc(UUID provinceId, String name);

}
