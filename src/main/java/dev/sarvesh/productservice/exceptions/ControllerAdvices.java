package dev.sarvesh.productservice.exceptions;

import dev.sarvesh.productservice.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException ex){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND,ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
