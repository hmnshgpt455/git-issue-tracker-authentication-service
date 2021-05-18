package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.services;

import io.github.hmnshgpt455.common.request.SignUpRequest;
import io.github.hmnshgpt455.common.responses.SignUpResponse;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.domain.UserAuthentication;
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
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        UserAuthentication userAuthenticationEntity = UserAuthentication.builder()
                .email(signUpRequest.getEmail())
                .username(signUpRequest.getUsername())
                .password(signUpRequest.getPassword())
                .roles(roleService.findPersistedRoleEntitiesByNames(signUpRequest.getRoles()))
                .build();

        UserAuthentication persistedEntity = userAuthenticationRepository.save(userAuthenticationEntity);

        return SignUpResponse.builder()
                .email(persistedEntity.getEmail())
                .username(persistedEntity.getUsername())
                .build();
    }
}