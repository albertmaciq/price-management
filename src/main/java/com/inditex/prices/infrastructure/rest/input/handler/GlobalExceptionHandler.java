package com.inditex.prices.infrastructure.rest.input.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleBadRequestException(final MethodArgumentTypeMismatchException ex,
                                                               final WebRequest request) {
        log.info("Path variable mismatch");
        log.error(ex.getLocalizedMessage());

        return handleExceptionInternal(ex,
            new GlobalHandlerErrorResponse(ErrorConstants.BAD_REQUEST_ERROR_CODE,
                "ERROR", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ErrorConstants.BAD_REQUEST_ERROR_DESC),
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex,
                                                                          final HttpHeaders headers,
                                                                          final HttpStatus status,
                                                                          final WebRequest request) {
        log.info("Missing request param");
        log.error(ex.getLocalizedMessage());

        return handleExceptionInternal(ex,
            new GlobalHandlerErrorResponse(ErrorConstants.BAD_REQUEST_ERROR_CODE,
                "ERROR", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ErrorConstants.BAD_REQUEST_ERROR_DESC),
            headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleSQLException(final SQLException ex,
                                                        final WebRequest request) {
        log.info("No data found in the database");
        log.error(ex.getLocalizedMessage());

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
