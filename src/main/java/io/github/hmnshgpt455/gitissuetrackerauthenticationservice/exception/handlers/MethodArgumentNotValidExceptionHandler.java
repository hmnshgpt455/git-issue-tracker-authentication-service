package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.handlers;

import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.error.MethodArgumentNotValidError;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.logger.ErrorLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class MethodArgumentNotValidExceptionHandler {

    private static final String BAD_REQUEST = "BAD_REQUEST";
    private final ErrorLogger<MethodArgumentNotValidError> eLogger;

    @SuppressWarnings("ConstantConditions")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<MethodArgumentNotValidError> handle(MethodArgumentNotValidException exception, WebRequest request) {
        MethodArgumentNotValidError error = MethodArgumentNotValidError.builder()
                .errorMessage(exception.getBindingResult().getFieldError().getDefaultMessage())
                .errorCode(BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .fieldNames(new ArrayList<>(Collections.singleton(exception.getFieldError().getField())))
                .requestUri(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();

        MultiValueMap<String, String> httpHeaders = new HttpHeaders();
        httpHeaders.add("error-UUID", UUID.randomUUID().toString());
        httpHeaders.add("error-Code", BAD_REQUEST);

        eLogger.log(error, ((ServletWebRequest)request).getRequest().getRequestURI(), exception, httpHeaders);

        return new ResponseEntity<>(error, httpHeaders, HttpStatus.BAD_REQUEST);
    }

}
