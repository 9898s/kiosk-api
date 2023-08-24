package com.example.kiosk.global.model;

import com.example.kiosk.global.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorResponse {
    private ErrorCode errorCode;
    private String errorMessage;
}
