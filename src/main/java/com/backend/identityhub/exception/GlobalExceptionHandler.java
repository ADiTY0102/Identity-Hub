package com.backend.identityhub.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.identityhub.dto.response.ApiResponseDTO;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDTO> handleUserAlreadyExists(
            UserAlreadyExistsException ex) {

        ApiResponseDTO response = ApiResponseDTO.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
}
