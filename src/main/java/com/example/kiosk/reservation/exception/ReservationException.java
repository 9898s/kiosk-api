package com.example.kiosk.reservation.exception;

import com.example.kiosk.global.type.ErrorCode;
import lombok.Getter;

@Getter
public class ReservationException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public ReservationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
