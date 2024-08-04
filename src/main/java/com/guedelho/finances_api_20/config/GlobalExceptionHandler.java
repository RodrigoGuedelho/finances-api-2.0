package com.guedelho.finances_api_20.config;

import com.guedelho.finances_api_20.dtos.response.ResponseException;
import org.apache.coyote.BadRequestException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus()
    public ResponseEntity<Object> badRequestException(BadRequestException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ResponseException response = new ResponseException(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(response) ;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> constraintViolationException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();

        BindingResult result = ex.getBindingResult();
        String errorMessage[] = {};
        for (FieldError error : result.getFieldErrors())
            errors.add(error.getDefaultMessage());

        ResponseException response = new ResponseException(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(response) ;
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus()
    public ResponseEntity<Object> badCredentialsException(BadCredentialsException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ResponseException response = new ResponseException(HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(), errors);
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(response) ;
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseStatus()
    public ResponseEntity<Object> internalAuthenticationServiceException(InternalAuthenticationServiceException ex) {
        List<String> errors = new ArrayList<>();
        errors.add("Usuário não existe.");
        ResponseException response = new ResponseException(HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(), errors);
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(response) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ResponseException response = new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), errors);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response) ;
    }
}
