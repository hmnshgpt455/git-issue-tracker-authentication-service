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
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDTO extends RepresentationModel<ProjectDTO> implements Serializable {

    static final long serialVersionUID = 2940681203378312652L;

    @Null
    private UUID id = null;

    @Null
    private Long version = null;

    @Null
    private OffsetDateTime createdDate = null;

    @Null
    private OffsetDateTime lastModifiedDate = null;

    @NotNull
    private String projectName;
}
