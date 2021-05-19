package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.services;

import io.github.hmnshgpt455.common.model.UserAuthenticationDTO;
import io.github.hmnshgpt455.common.model.UserDTO;
import io.github.hmnshgpt455.common.responses.AvailabilityResponse;
import io.github.hmnshgpt455.common.responses.SignUpResponse;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.config.JmsConfig;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.domain.UserAuthentication;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.exception.definitions.InvalidRequest;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.mappers.UserDtoAndUserAuthenticationDtoMapper;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.repositories.UserAuthenticationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final UserAuthenticationRepository userAuthenticationRepository;
    private final RoleService roleService;
    private final UserDtoAndUserAuthenticationDtoMapper userDtoAndUserAuthenticationDtoMapper;
    private final JmsTemplate jmsTemplate;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResponse signUp(UserAuthenticationDTO userAuthenticationDTO) {

        if (userAuthenticationRepository.existsByEmail(userAuthenticationDTO.getEmail())) {
            throw new InvalidRequest("Email already present");
        }

        UserAuthentication userAuthenticationEntity = UserAuthentication.builder()
                .email(userAuthenticationDTO.getEmail())
                .username(userAuthenticationDTO.getUsername())
                .password(encodePassword(userAuthenticationDTO.getPassword()))
                .roles(roleService.findPersistedRoleEntitiesByNames(userAuthenticationDTO.getRoles()))
                .build();

        UserAuthentication persistedEntity = userAuthenticationRepository.save(userAuthenticationEntity);
        sendMessageToSaveUserDetails(userAuthenticationDTO);

        return SignUpResponse.builder()
                .email(persistedEntity.getEmail())
                .username(persistedEntity.getUsername())
                .build();
    }

    private String encodePassword(String password) {
        String base64DecodedPassword = new String(Base64.getDecoder().decode(password));
        return passwordEncoder.encode(base64DecodedPassword);
    }

    private void sendMessageToSaveUserDetails(UserAuthenticationDTO userAuthenticationDTO) {
        UserDTO userDTO = userDtoAndUserAuthenticationDtoMapper.userAuthenticationDtoToUserDto(userAuthenticationDTO);
        jmsTemplate.convertAndSend(JmsConfig.NEW_USER_QUEUE, userDTO);
    }

    @Override
    public AvailabilityResponse checkUsernameAvailability(String username) {
        return AvailabilityResponse.builder()
                .isAvailable(!userAuthenticationRepository.existsByUsername(username))
                .build();
    }
}
