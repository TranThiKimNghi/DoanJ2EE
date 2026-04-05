//package com.booking.Booking.application.service;
//
//import com.booking.Booking.adapters.dto.Request.BookingDetailRequestDTO;
//import com.booking.Booking.adapters.dto.Response.BookingDetailResponseDTO;
//import com.booking.Booking.application.mapper.BookingDetailMapper;
//import com.booking.Booking.domain.model.BookingDetail;
//import com.booking.Booking.infrastructure.persistence.entity.BookingDetailsEntity;
//import com.booking.Booking.infrastructure.persistence.repository.BookingDetailsRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class BookingDetailService {
//    private final BookingDetailsRepository bookingDetailRepository;
//
//    // CREATE
//    public BookingDetailResponseDTO create(BookingDetailRequestDTO request) {
//        BookingDetail domain = BookingDetailMapper.fromRequestDTO(request);
//        domain.setId(UUID.randomUUID());
//        domain.setCreatedAt(LocalDateTime.now());
//        domain.setUpdatedAt(LocalDateTime.now());
//
//        BookingDetailsEntity saved = bookingDetailRepository.save(
//                BookingDetailMapper.toEntity(domain)
//        );
//
//        return BookingDetailMapper.toResponseDTO(BookingDetailMapper.toDomain(saved));
//    }
//
//    // GET ALL
//    public List<BookingDetailResponseDTO> getAll() {
//        return bookingDetailRepository.findAll()
//                .stream()
//                .filter(e -> !e.getIsDeleted())
//                .map(BookingDetailMapper::toDomain)
//                .map(BookingDetailMapper::toResponseDTO)
//                .toList();
//    }
//
//    // GET BY BOOKING
//    public List<BookingDetailResponseDTO> getByBooking(UUID bookingId) {
//        return bookingDetailRepository.findByBookingId(bookingId)
//                .stream()
//                .filter(e -> !e.getIsDeleted())
//                .map(BookingDetailMapper::toDomain)
//                .map(BookingDetailMapper::toResponseDTO)
//                .toList();
//    }
//
//    // GET BY HOTEL
//    public List<BookingDetailResponseDTO> getByHotel(UUID hotelId) {
//        return bookingDetailRepository.findByHotelId(hotelId)
//                .stream()
//                .filter(e -> !e.getIsDeleted())
//                .map(BookingDetailMapper::toDomain)
//                .map(BookingDetailMapper::toResponseDTO)
//                .toList();
//    }
//
//    // GET BY ROOM
//    public List<BookingDetailResponseDTO> getByRoom(UUID roomId) {
//        return bookingDetailRepository.findByRoomId(roomId)
//                .stream()
//                .filter(e -> !e.getIsDeleted())
//                .map(BookingDetailMapper::toDomain)
//                .map(BookingDetailMapper::toResponseDTO)
//                .toList();
//    }
//
//    // UPDATE
//    public BookingDetailResponseDTO update(UUID id, BookingDetailRequestDTO request) {
//        BookingDetailsEntity entity = bookingDetailRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Booking detail not found"));
//
//        BookingDetail domain = BookingDetailMapper.fromRequestDTO(request);
//        domain.setId(id);
//        domain.setCreatedAt(entity.getCreatedAt()); // giữ createdAt cũ
//        domain.setUpdatedAt(LocalDateTime.now());
//
//        BookingDetailsEntity updatedEntity = bookingDetailRepository.save(
//                BookingDetailMapper.toEntity(domain)
//        );
//
//        return BookingDetailMapper.toResponseDTO(BookingDetailMapper.toDomain(updatedEntity));
//    }
//
//    // SOFT DELETE
//    public void delete(UUID id) {
//        BookingDetailsEntity entity = bookingDetailRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Booking detail not found"));
//
//        entity.setIsDeleted(true);
//        entity.setUpdatedAt(LocalDateTime.now());
//
//        bookingDetailRepository.save(entity);
//    }
//}
