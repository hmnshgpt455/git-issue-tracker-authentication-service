package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.logger;

import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.error.BaseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

@Component
@Slf4j
public class ErrorLogger {
    public void log(BaseError error, String serviceURL, Exception exception, MultiValueMap<String, String> httpHeaders) {
        log.error("\n\n----------Error occurred in service : " + serviceURL + "------------");
        log.error(httpHeaders.getFirst("error-UUID"));
        log.error(error.toString());
        Optional.ofNullable(exception.getCause()).ifPresent(cause -> log.error(cause.toString()));
        exception.printStackTrace();
        log.error("-----------------");
    }
}
