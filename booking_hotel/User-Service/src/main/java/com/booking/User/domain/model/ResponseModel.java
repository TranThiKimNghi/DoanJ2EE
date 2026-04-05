package com.booking.User.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T> {
    private String resultCode;               // SUCCESS / ERROR
    private T data;                          // Payload trả về
    private List<ResponseErrorModel> errors; // Danh sách lỗi nếu có

    // ================== Static factory ==================

    public static <T> ResponseModel<T> success(T data) {
        return ResponseModel.<T>builder()
                .resultCode("SUCCESS")
                .data(data)
                .build();
    }

    public static <T> ResponseModel<T> error(ResponseErrorModel error) {
        List<ResponseErrorModel> list = new ArrayList<>();
        list.add(error);
        return ResponseModel.<T>builder()
                .resultCode("ERROR")
                .errors(list)
                .build();
    }

    public ResponseModel<T> addError(ResponseErrorModel error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
        return this;
    }
}
