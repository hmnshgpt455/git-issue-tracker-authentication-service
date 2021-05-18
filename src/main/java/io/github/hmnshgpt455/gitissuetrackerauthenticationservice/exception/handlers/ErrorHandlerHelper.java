package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.handlers;

import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.error.BaseError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ErrorHandlerHelper {

    public MultiValueMap<String, String> getHttpHeadersMap(String errorCode) {
        MultiValueMap<String, String> httpHeaders = new HttpHeaders();
        httpHeaders.add("error-UUID", UUID.randomUUID().toString());
        httpHeaders.add("error-Code", errorCode);
        return httpHeaders;
    }

    public BaseError buildErrorObject(BaseError.BaseErrorBuilder<?, ?> builder,
                                      String errorCode, int httpStatusValue, String requestURI, String errorMessage) {

        return builder.timestamp(OffsetDateTime.now())
                .errorCode(errorCode)
                .requestUri(requestURI)
                .statusCode(httpStatusValue)
                .errorMessage(errorMessage)
                .build();
    }
}
