package com.booking.Booking.application.mapper;
import com.booking.Booking.adapters.dto.Response.BookingResponseDTO;
import com.booking.Booking.infrastructure.persistence.entity.BookingDetailsEntity;
import com.booking.Booking.infrastructure.persistence.entity.BookingEntity;
public class BookingMapper {
    public static BookingResponseDTO toResponseDTO(
            BookingEntity booking,
            BookingDetailsEntity detail,
            String hotelName,
            String roomNumber
    ) {
        return BookingResponseDTO.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .hotelId(booking.getHotelId())
                .hotelName(hotelName)
                .roomId(detail != null ? detail.getRoomId() : null)
                .roomNumber(roomNumber)
                .checkingDate(booking.getCheckingDate())
                .checkoutDate(booking.getCheckoutDate())
                .status(booking.getStatus())
                .totalAmount(detail != null ? detail.getPrice() : booking.getTotalAmount())
                .build();
    }
}
