package com.example.kiosk.shop.exception;

import com.example.kiosk.global.type.ErrorCode;
import lombok.Getter;

@Getter
public class ShopException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public ShopException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
