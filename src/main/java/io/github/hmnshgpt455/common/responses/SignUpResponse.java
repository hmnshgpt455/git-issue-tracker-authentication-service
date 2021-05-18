package io.github.hmnshgpt455.common.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpResponse implements Serializable {

    static final long serialVersionUID = 5022627454077690010L;

    private String username;
    private String email;
}
