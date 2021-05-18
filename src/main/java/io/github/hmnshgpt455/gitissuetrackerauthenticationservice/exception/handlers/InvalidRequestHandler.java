package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.handlers;

import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.definitions.InvalidRequest;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.error.BaseError;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.logger.ErrorLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RequiredArgsConstructor
@Order(100)
public class InvalidRequestHandler {

    private final ErrorLogger eLogger;
    private final ErrorHandlerHelper errorHandlerHelper;

    private static final String BAD_REQUEST = "BAD_REQUEST";

    @ExceptionHandler(InvalidRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseError> handleInvalidRequest(InvalidRequest exception, WebRequest request) {

        BaseError error = errorHandlerHelper.buildErrorObject(BaseError.builder(), BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(), ((ServletWebRequest) request).getRequest().getRequestURI(),
                exception.getMessage());

        MultiValueMap<String, String> httpHeaders = errorHandlerHelper.getHttpHeadersMap(BAD_REQUEST);

        eLogger.log(error, ((ServletWebRequest)request).getRequest().getRequestURI(), exception, httpHeaders);

        return new ResponseEntity<>(error, httpHeaders, HttpStatus.BAD_REQUEST);
    }

}
