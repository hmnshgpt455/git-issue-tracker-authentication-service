package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.error;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MethodArgumentNotValidError extends BaseError {

    private List<String> fieldNames;

    public MethodArgumentNotValidError(Integer statusCode, String errorMessage, String errorCode, String requestUri, OffsetDateTime timestamp, List<String> fieldNames) {
        super(statusCode, errorMessage, errorCode, requestUri, timestamp);
        this.fieldNames = fieldNames;
    }
}
