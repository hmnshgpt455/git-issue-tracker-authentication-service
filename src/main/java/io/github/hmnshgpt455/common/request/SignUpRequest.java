package io.github.hmnshgpt455.common.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpRequest implements Serializable {

    static final long serialVersionUID = -8479810303125337575L;

    @NotNull
    private String firstName;
    private String lastName;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    @NotEmpty
    private Set<String> roles = new HashSet<>();

    @NotNull
    private String organization;

    @NotNull
    private String password;

    private Set<String> projects = new HashSet<>();
}
