package com.booking.Common.application.mapper;

import com.booking.Common.adapters.dto.NearbyPlaceDTO;
import com.booking.Common.domain.model.NearbyPlace;
import com.booking.Common.infrastructure.persistence.entity.NearbyPlaceEntity;
import org.springframework.stereotype.Component;

@Component

public class NearbyPlaceMapper {
    public NearbyPlaceDTO toDTO(NearbyPlaceEntity place, double score) {
        return NearbyPlaceDTO.builder()
                .id(place.getId())
                .placeName(place.getPlaceName())
                .category(place.getCategory())
                .rating(place.getRating())
                .numReviews(place.getNumReviews())
                .address(place.getAddress())
                .distance(place.getDistance())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .score(score)
                .build();
    }
}
