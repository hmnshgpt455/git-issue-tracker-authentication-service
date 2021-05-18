package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

@Component
@Slf4j
public class ErrorLogger<T> {
    public void log(T error, String serviceURL, Exception exception, MultiValueMap<String, String> httpHeaders) {
        log.error("\n\n----------Error occurred in service : " + serviceURL + "------------");
        log.error(httpHeaders.getFirst("error-UUID"));
        log.error(error.toString());
        Optional.ofNullable(exception.getCause()).ifPresent(cause -> log.error(cause.toString()));
        exception.printStackTrace();
        log.error("-----------------");
    }
}
