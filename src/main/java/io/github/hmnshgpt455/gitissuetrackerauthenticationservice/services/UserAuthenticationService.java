package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.services;

import io.github.hmnshgpt455.common.model.UserAuthenticationDTO;
import io.github.hmnshgpt455.common.responses.AvailabilityResponse;
import io.github.hmnshgpt455.common.responses.SignUpResponse;

public interface UserAuthenticationService {

    SignUpResponse signUp(UserAuthenticationDTO userAuthenticationDTO);

    AvailabilityResponse checkUsernameAvailability(String username);
}
