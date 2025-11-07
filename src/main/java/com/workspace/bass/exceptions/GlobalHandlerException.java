package com.workspace.bass.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class GlobalHandlerException {

    private static final Map<Class<? extends Exception>, HttpStatus> exceptionStatusMap = new HashMap<>();

    static {
        exceptionStatusMap.put(ResourceNotFoundException.class, HttpStatus.NOT_FOUND);
        exceptionStatusMap.put(IllegalArgumentException.class, HttpStatus.BAD_REQUEST);
        exceptionStatusMap.put(UnauthorizedException.class, HttpStatus.UNAUTHORIZED);
        exceptionStatusMap.put(ResourceException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionStatusMap.put(ObjectValidationException.class, HttpStatus.BAD_REQUEST);
        // add mores ...
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception ex) {
        HttpStatus status = exceptionStatusMap.getOrDefault(
                ex.getClass(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        String message = ex.getMessage();
        Set<String> errors = null;

        if (ex instanceof ObjectValidationException) {
            errors = ((ObjectValidationException) ex).getErrors();
        }
        ErrorResponse error = new ErrorResponse(
                message,
                status.value(),
                LocalDateTime.now(),
                errors
        );

        return ResponseEntity.status(status).body(error);
    }
}
