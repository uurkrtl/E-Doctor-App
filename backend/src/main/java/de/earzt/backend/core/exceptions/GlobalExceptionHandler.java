package de.earzt.backend.core.exceptions;

import de.earzt.backend.core.exceptions.types.DuplicateRecordException;
import de.earzt.backend.core.exceptions.types.HaveActiveRecordException;
import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.core.exceptions.types.RecordPassiveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateRecordException (DuplicateRecordException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.CONFLICT)
                .title("Duplicate Record")
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.NOT_FOUND)
                .title("Record Not Found")
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecordPassiveException.class)
    public ResponseEntity<ErrorResponse> handleRecordPassiveException(RecordPassiveException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST)
                .title("Record Passive")
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HaveActiveRecordException.class)
    public ResponseEntity<ErrorResponse> handleHaveActiveRecordException(HaveActiveRecordException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST)
                .title("Record Have Active Records")
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request){
        StringBuilder errorMessages = new StringBuilder();
        errorMessages.append("Error: ");
        ex.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append(" - "));
        ErrorResponse errorResponse = ErrorResponse.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST)
                .title("Data validation error")
                .message(errorMessages.charAt(errorMessages.length()-2)=='-' ? errorMessages.substring(0,errorMessages.length()-3) : errorMessages.toString())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .title("An unexpected error occurred")
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
