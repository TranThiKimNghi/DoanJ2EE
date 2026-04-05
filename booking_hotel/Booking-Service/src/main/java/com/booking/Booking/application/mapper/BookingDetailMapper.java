//package com.booking.Booking.application.mapper;
//
//import com.booking.Booking.adapters.dto.Request.BookingDetailRequestDTO;
//import com.booking.Booking.adapters.dto.Response.BookingDetailResponseDTO;
//import com.booking.Booking.domain.model.BookingDetail;
//import com.booking.Booking.infrastructure.persistence.entity.BookingDetailsEntity;
//
//import java.time.LocalDateTime;
//
//public class BookingDetailMapper {
//    public static BookingDetail toDomain(BookingDetailsEntity entity) {
//        if(entity == null) return null;
//        BookingDetail domain = new BookingDetail();
//        domain.setId(entity.getId());
//        domain.setBookingId(entity.getBookingId());
//        domain.setHotelId(entity.getHotelId());
//        domain.setRoomId(entity.getRoomId());
//        domain.setPrice(entity.getPrice());
//        domain.setGuestCount(entity.getGuestCount());
//        domain.setStatus(entity.getStatus());
//        domain.setCreatedAt(entity.getCreatedAt());
//        domain.setUpdatedAt(entity.getUpdatedAt());
//        domain.setDeleted(entity.getIsDeleted());
//        return domain;
//    }
//
//    public static BookingDetailsEntity toEntity(BookingDetail domain) {
//        if(domain == null) return null;
//        BookingDetailsEntity entity = new BookingDetailsEntity();
//        entity.setId(domain.getId());
//        entity.setBookingId(domain.getBookingId());
//        entity.setHotelId(domain.getHotelId());
//        entity.setRoomId(domain.getRoomId());
//        entity.setPrice(domain.getPrice());
//        entity.setGuestCount(domain.getGuestCount());
//        entity.setStatus(domain.getStatus());
//        entity.setCreatedAt(domain.getCreatedAt());
//        entity.setUpdatedAt(domain.getUpdatedAt());
//        entity.setIsDeleted(domain.isDeleted());
//        return entity;
//    }
//
//    public static BookingDetailResponseDTO toResponseDTO(BookingDetail domain) {
//        if(domain == null) return null;
//        return new BookingDetailResponseDTO(
//                domain.getId(),
//                domain.getBookingId(),
//                domain.getHotelId(),
//                domain.getRoomId(),
//                domain.getPrice(),
//                domain.getGuestCount(),
//                domain.getStatus()
//        );
//    }
//
//    public static BookingDetail fromRequestDTO(BookingDetailRequestDTO dto) {
//        if(dto == null) return null;
//        BookingDetail domain = new BookingDetail();
//        domain.setBookingId(dto.getBookingId());
//        domain.setHotelId(dto.getHotelId());
//        domain.setRoomId(dto.getRoomId());
//        domain.setPrice(dto.getPrice());
//        domain.setGuestCount(dto.getGuestCount());
//        domain.setCreatedAt(LocalDateTime.now());
//        domain.setUpdatedAt(LocalDateTime.now());
//        domain.setDeleted(false);
//        return domain;
//    }
//}
