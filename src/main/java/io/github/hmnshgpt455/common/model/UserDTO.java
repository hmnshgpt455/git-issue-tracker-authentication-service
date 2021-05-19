package io.github.hmnshgpt455.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> implements Serializable   {

    static final long serialVersionUID = -4133151599555050916L;

    @Null
    private UUID id = null;

    @Null
    private Long version = null;

    @Null
    private OffsetDateTime createdDate = null;

    @Null
    private OffsetDateTime lastModifiedDate = null;

    @NotNull
    private String firstName;
    private String lastName;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private Set<RoleDTO> roles = new HashSet<>();

    @NotNull
    private OrganizationDTO organization;

    private Set<ProjectDTO> projects = new HashSet<>();

}
