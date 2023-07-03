package com.pedro256.app.exceptions.handler;

import com.pedro256.app.exceptions.BadRequestException;
import com.pedro256.app.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public  final ResponseEntity<ExceptionResponse> handleGenericException(Exception exc, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                exc.getMessage(),
                webRequest.getDescription(false));
        return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public  final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception exc, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                exc.getMessage(),
                webRequest.getDescription(false));
        return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
