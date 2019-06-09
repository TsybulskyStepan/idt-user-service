package com.idt.assignment.api;

import com.idt.assignment.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Exception handler and processor
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    /**
     * General exception handler. Prevents sending sensitive information
     * @param e exception that occurred
     * @return sends reason of happened exception
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> exceptionHandler(Throwable e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Server error occurred");
    }

    /**
     * Business exception handler. Processes business related exceptions
     * @param e exception that occurred
     * @return sends reason of happened exception
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> businessExceptionHandler(Throwable e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
