package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.error;

import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.logger.ErrorLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.UUID;

@ControllerAdvice
@RequiredArgsConstructor
public class GenericExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    private final ErrorLogger<BaseError> eLogger;

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<BaseError> handleGenericException(RuntimeException exception, WebRequest request) {
        BaseError error = BaseError.builder()
                .errorMessage(exception.getMessage())
                .errorCode(INTERNAL_SERVER_ERROR)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .requestUri(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();

        MultiValueMap<String, String> httpHeaders = new HttpHeaders();
        httpHeaders.add("error-UUID", UUID.randomUUID().toString());
        httpHeaders.add("error-Code", INTERNAL_SERVER_ERROR);

        eLogger.log(error, ((ServletWebRequest)request).getRequest().getRequestURI(), exception, httpHeaders);

        return new ResponseEntity<>(error, httpHeaders, HttpStatus.BAD_REQUEST);
    }
}
