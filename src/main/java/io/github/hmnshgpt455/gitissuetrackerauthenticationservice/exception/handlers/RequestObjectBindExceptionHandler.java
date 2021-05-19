package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.handlers;

import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.error.BaseError;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.error.MethodArgumentNotValidError;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.logger.ErrorLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class RequestObjectBindExceptionHandler {

    private static final String BAD_REQUEST = "BAD_REQUEST";
    private final ErrorLogger eLogger;
    private final ErrorHandlerHelper errorHandlerHelper;

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<MethodArgumentNotValidError> handle(BindException exception, WebRequest request) {

        return handleExceptionAndReturnResponseEntity(exception, (ServletWebRequest) request);
    }

    private ResponseEntity<MethodArgumentNotValidError> handleExceptionAndReturnResponseEntity(BindException exception, ServletWebRequest request) {
        BaseError baseError = errorHandlerHelper.buildErrorObject(MethodArgumentNotValidError.builder(), BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(), request.getRequest().getRequestURI(),
                Objects.requireNonNull(exception.getFieldError()).getDefaultMessage());

        MethodArgumentNotValidError error = (MethodArgumentNotValidError) baseError;
        error.setFieldNames(new ArrayList<>(Collections.singleton(exception.getFieldError().getField())));

        MultiValueMap<String, String> httpHeaders = errorHandlerHelper.getHttpHeadersMap(BAD_REQUEST);

        eLogger.log(error, request.getRequest().getRequestURI(), exception, httpHeaders);

        return new ResponseEntity<>(error, httpHeaders, HttpStatus.BAD_REQUEST);
    }

}
