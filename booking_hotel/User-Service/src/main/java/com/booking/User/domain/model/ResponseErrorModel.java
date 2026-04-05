package com.booking.User.domain.model;

import com.booking.User.application.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseErrorModel {
    private String code;                 // Mã lỗi chuẩn trong hệ thống
    private String message;              // Thông báo lỗi (ngắn gọn)
    private String description;          // Mô tả lỗi chi tiết (cho dev)
    private String traceId;              // Log tracing giữa các microservice
    private String level;                // INFO / WARN / ERROR / CRITICAL

    @JsonFormat(pattern = CommonConstant.DATETIME_FORMAT)
    private LocalDateTime serverDateTime;
}
