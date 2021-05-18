package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.handlers;

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
@Order(999)
public class GenericExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    private final ErrorLogger eLogger;
    private final ErrorHandlerHelper errorHandlerHelper;

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<BaseError> handleGenericException(RuntimeException exception, WebRequest request) {

        BaseError error = errorHandlerHelper.buildErrorObject(BaseError.builder(), INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(), ((ServletWebRequest) request).getRequest().getRequestURI(),
                exception.getMessage());

        MultiValueMap<String, String> httpHeaders = errorHandlerHelper.getHttpHeadersMap(INTERNAL_SERVER_ERROR);

        eLogger.log(error, ((ServletWebRequest)request).getRequest().getRequestURI(), exception, httpHeaders);

        return new ResponseEntity<>(error, httpHeaders, HttpStatus.BAD_REQUEST);
    }
}
