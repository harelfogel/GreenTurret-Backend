package com.greenturret.exception.handler;

import com.greenturret.response.ApiResponse;
import com.greenturret.response.EResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.greenturret.exception.UsernameAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
        ApiResponse response = new ApiResponse(EResponseStatus.ERROR,ex.getMessage(),null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}
