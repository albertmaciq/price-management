package com.inditex.prices.infrastructure.rest.input.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MissingPathVariableException.class)
    protected ResponseEntity<Object> handleMissingPathVariableException(final MissingPathVariableException ex,
                                                                        final WebRequest request) {
        logger.error(ex.getLocalizedMessage(), ex);

        return handleExceptionInternal(ex,
            new GlobalHandlerErrorResponse(ErrorConstants.BAD_REQUEST_ERROR_CODE,
                "ERROR", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ErrorConstants.BAD_REQUEST_ERROR_PATH_VARIABLE_DESC),
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Object> handleMissingRequestParamException(final MissingServletRequestParameterException ex,
                                                                        final WebRequest request) {
        logger.error(ex.getLocalizedMessage(), ex);

        return handleExceptionInternal(ex,
            new GlobalHandlerErrorResponse(ErrorConstants.BAD_REQUEST_ERROR_CODE,
                "ERROR", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ErrorConstants.BAD_REQUEST_ERROR_REQUEST_PARAM_DESC),
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleSQLException(final SQLException ex,
                                                        final WebRequest request) {
        logger.error(ex.getLocalizedMessage(), ex);

        return handleExceptionInternal(ex,
            new GlobalHandlerErrorResponse(ErrorConstants.INTERNAL_SERVER_ERROR_CODE,
                "ERROR", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ErrorConstants.INTERNAL_SERVER_ERROR_DESC),
            new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(final RuntimeException ex, final WebRequest request) {
        logger.error(ex.getLocalizedMessage(), ex);

        return handleExceptionInternal(ex,
            new GlobalHandlerErrorResponse(ErrorConstants.INTERNAL_SERVER_ERROR_CODE,
                "ERROR", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ErrorConstants.INTERNAL_SERVER_ERROR_DESC),
            new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
