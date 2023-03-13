package com.inditex.prices.infrastructure.rest.input.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PathVariableException.class)
    protected ResponseEntity<Object> handleBadRequestException(final PathVariableException ex,
                                                               final WebRequest request) {
        log.error(HttpStatus.BAD_REQUEST.getReasonPhrase());

        String detailedDescription = String.format("%s path variable is/are missing in %d index",
            ex.getPathVariable(), ex.getIndex());

        return handleExceptionInternal(ex,
            new GlobalHandlerErrorResponse(ex.getCode(),
                "ERROR", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                detailedDescription),
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RequestParamException.class)
    protected ResponseEntity<Object> handleBadRequestException(final RequestParamException ex,
                                                               final WebRequest request) {
        log.error(HttpStatus.BAD_REQUEST.getReasonPhrase());

        String detailedDescription = String.format("%s request param is missing", ex.getParam());

        return handleExceptionInternal(ex,
            new GlobalHandlerErrorResponse(ex.getCode(),
                ex.getLevel(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                detailedDescription),
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleSQLException(final SQLException ex,
                                                        final WebRequest request) {
        log.error("No data found in the data base");

        return handleExceptionInternal(ex,
            new GlobalHandlerErrorResponse(ErrorConstants.NOT_FOUND_ERROR_CODE,
                "ERROR", HttpStatus.NOT_FOUND.getReasonPhrase(),
                ErrorConstants.NOT_FOUND_ERROR_DESC),
            new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(final RuntimeException ex, final WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);

        return handleExceptionInternal(ex,
            new GlobalHandlerErrorResponse(ErrorConstants.INTERNAL_SERVER_ERROR_CODE,
                "ERROR", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ErrorConstants.INTERNAL_SERVER_ERROR_DESC),
            new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
