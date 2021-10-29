package com.mutant.config;

import com.mutant.error.ApiError;
import com.mutant.exception.MutantException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private final Map<Integer, HttpStatus> mapIntegerToHttpStatus = new HashMap<Integer, HttpStatus>();;

    public RestResponseEntityExceptionHandler() {
        for (HttpStatus httpCode: HttpStatus.values()
        ) {
            mapIntegerToHttpStatus.put(httpCode.value(), httpCode);
        }
    }

    @ExceptionHandler(value = {MutantException.class})
    protected ResponseEntity<ApiError> handleConflict(
            MutantException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<ApiError>(new ApiError().code(ex.getCode())
                .message(ex.getMessage())
                .cause(ex.getCause() == null ? null : ex.getCause().toString()), mapIntegerToHttpStatus.get(ex.getCode()));
    }
}
