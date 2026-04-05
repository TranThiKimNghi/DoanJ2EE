    package com.booking.Booking.adapters.dto;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public class BookingUpdateDTO {
        private LocalDateTime checkingDate;
        private LocalDateTime checkoutDate;
        private String status;
        private BigDecimal totalAmount;
    }
