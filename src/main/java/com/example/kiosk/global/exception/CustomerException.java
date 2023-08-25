package com.example.kiosk.global.exception;

import com.example.kiosk.global.type.ErrorCode;
import lombok.Getter;

@Getter
public class CustomerException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public CustomerException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
