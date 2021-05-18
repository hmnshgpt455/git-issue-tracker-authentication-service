package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.services;

import io.github.hmnshgpt455.common.model.UserAuthenticationDTO;
import io.github.hmnshgpt455.common.responses.AvailabilityResponse;
import io.github.hmnshgpt455.common.responses.SignUpResponse;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.domain.UserAuthentication;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.definitions.InvalidRequest;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.repositories.UserAuthenticationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final UserAuthenticationRepository userAuthenticationRepository;
    private final RoleService roleService;

    @Override
    public SignUpResponse signUp(UserAuthenticationDTO userAuthenticationDTO) {

        if (userAuthenticationRepository.existsByEmail(userAuthenticationDTO.getEmail())) {
            throw new InvalidRequest("Email already present");
        }

        UserAuthentication userAuthenticationEntity = UserAuthentication.builder()
                .email(userAuthenticationDTO.getEmail())
                .username(userAuthenticationDTO.getUsername())
                .password(userAuthenticationDTO.getPassword())
                .roles(roleService.findPersistedRoleEntitiesByNames(userAuthenticationDTO.getRoles()))
                .build();

        UserAuthentication persistedEntity = userAuthenticationRepository.save(userAuthenticationEntity);

        return SignUpResponse.builder()
                .email(persistedEntity.getEmail())
                .username(persistedEntity.getUsername())
                .build();
    }

    @Override
    public AvailabilityResponse checkUsernameAvailability(String username) {
        return AvailabilityResponse.builder()
                .isAvailable(!userAuthenticationRepository.existsByUsername(username))
                .build();
    }
}
