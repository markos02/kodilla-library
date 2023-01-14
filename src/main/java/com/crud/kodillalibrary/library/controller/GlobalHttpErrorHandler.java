package com.crud.kodillalibrary.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReaderNotFoundException.class)
    public ResponseEntity<Object> handleReaderNotFoundException(ReaderNotFoundException exception) {
        return new ResponseEntity<>("Reader with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity<Object> handleTitleNotFoundException(TitleNotFoundException exception) {
        return new ResponseEntity<>("Title with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookCopyNotFoundException.class)
    public ResponseEntity<Object> handleBookCopyNotFoundException(BookCopyNotFoundException exception) {
        return new ResponseEntity<>("Book copy with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseEntity<Object> handleRentalNotFoundException(RentalNotFoundException exception) {
        return new ResponseEntity<>("Rental with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<Object> handleBookCopyNotAvailableException(BookNotAvailableException exception) {
        return new ResponseEntity<>("Book is not available", HttpStatus.BAD_REQUEST);
    }
}
