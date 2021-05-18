package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.error;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MethodArgumentNotValidError extends BaseError {

    private List<String> fieldNames;

    public MethodArgumentNotValidError(Integer statusCode, String errorMessage, String errorCode, String requestUri, List<String> fieldNames) {
        super(statusCode, errorMessage, errorCode, requestUri);
        this.fieldNames = fieldNames;
    }
}
