package com.example.kiosk.global.exception;

import com.example.kiosk.global.model.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.kiosk.global.type.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.example.kiosk.global.type.ErrorCode.INVALID_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException() {
        ErrorResponse errorResponse = new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler() {
        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.getDescription());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
