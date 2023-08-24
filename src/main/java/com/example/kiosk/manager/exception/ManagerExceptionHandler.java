package com.example.kiosk.manager.exception;

import com.example.kiosk.global.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManagerExceptionHandler {
    @ExceptionHandler(ManagerException.class)
    public ResponseEntity<?> managerExceptionHandler(ManagerException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getErrorMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
