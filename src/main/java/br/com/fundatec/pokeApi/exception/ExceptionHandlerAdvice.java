package br.com.fundatec.pokeApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerAdvice {


    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleNofFound(ObjectNotFoundException e) {
        return new ResponseEntity<>(new ApiErrorDTO(e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }


}
