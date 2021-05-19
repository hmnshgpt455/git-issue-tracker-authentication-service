package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.mappers;

import io.github.hmnshgpt455.common.model.OrganizationDTO;
import io.github.hmnshgpt455.common.model.ProjectDTO;
import io.github.hmnshgpt455.common.model.UserAuthenticationDTO;
import io.github.hmnshgpt455.common.model.UserDTO;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserDtoAndUserAuthenticationDtoMapper {

    @Mappings({
            @Mapping(source = "roles", target = "roles", ignore = true),
            @Mapping(source = "projects", target = "projects", ignore = true),
            @Mapping(source = "organization", target = "organization", ignore = true)
    })
    UserDTO userAuthenticationDtoToUserDto(UserAuthenticationDTO userAuthenticationDTO);

    @AfterMapping
    default void mapProjects(UserAuthenticationDTO userAuthenticationDTO, @MappingTarget UserDTO.UserDTOBuilder userDTO) {
        Set<ProjectDTO> projectDTOSet =  userAuthenticationDTO.getProjects().stream().map(project -> ProjectDTO.builder()
                .projectName(project)
                .build()).collect(Collectors.toSet());

        userDTO.projects(projectDTOSet)
                .build();
    }

    @AfterMapping
    default void mapOrganization(UserAuthenticationDTO userAuthenticationDTO, @MappingTarget UserDTO.UserDTOBuilder userDTO) {
        OrganizationDTO organizationDTO = OrganizationDTO.builder()
                .name(userAuthenticationDTO.getOrganization())
                .build();
        userDTO.organization(organizationDTO)
                .build();
    }
}
