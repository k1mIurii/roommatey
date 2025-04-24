package com.example.backend.exceptions;

import com.example.backend.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDTO> handleRecordNotFoundException(Exception exception, WebRequest webRequest) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .status(HttpStatus.OK.value())
                .message(exception.getMessage())
                .details(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.OK);
    }

}
