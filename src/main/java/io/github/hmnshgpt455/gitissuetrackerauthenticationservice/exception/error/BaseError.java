package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseError {
    private Integer statusCode;
    private String errorMessage;
    private String errorCode;
    private String requestUri;
}