package com.booking.Common.application.service;

import com.booking.Common.adapters.dto.NearbyPlaceDTO;
import com.booking.Common.application.mapper.NearbyPlaceMapper;
import com.booking.Common.domain.model.NearbyPlace;
import com.booking.Common.infrastructure.persistence.entity.NearbyPlaceEntity;
import com.booking.Common.infrastructure.persistence.repository.NearbyPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NearbyPlaceService {
    @Autowired
    private NearbyPlaceRepository nearbyPlaceRepository;

    @Autowired
    private NearbyPlaceMapper nearbyPlaceMapper;

    public List<NearbyPlaceDTO> getTopNearbyPlacesByHotelId(UUID hotelId, int topN) {
        List<NearbyPlaceEntity> places = nearbyPlaceRepository.findByHotelIdAndIsDeletedFalse(hotelId);
        if (places.isEmpty()) return Collections.emptyList();

        // Tìm max để chuẩn hóa
        int maxReviews = places.stream().mapToInt(p -> p.getNumReviews() != null ? p.getNumReviews() : 0).max().orElse(1);
        float maxDistance = places.stream().map(p -> p.getDistance() != null ? p.getDistance() : 0f).max(Float::compare).orElse(1f);

        double wRating = 0.5;
        double wReviews = 0.3;
        double wDistance = 0.2;

        return places.stream()
                .map(p -> {
                    double ratingVal = p.getRating() != null ? p.getRating().doubleValue() : 0.0;
                    double ratingNorm = ratingVal / 5.0;
                    double reviewsNorm = (double) (p.getNumReviews() != null ? p.getNumReviews() : 0) / maxReviews;
                    double distanceNorm = (double) (p.getDistance() != null ? p.getDistance() : 0f) / maxDistance;

                    double score = wRating * ratingNorm + wReviews * reviewsNorm - wDistance * distanceNorm;
                    return nearbyPlaceMapper.toDTO(p, score);
                })
                .sorted((a, b) -> Double.compare(b.getScore(), a.getScore())) // giảm dần
                .limit(topN)
                .toList();
    }
}
