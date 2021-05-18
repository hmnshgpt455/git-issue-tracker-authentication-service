package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.services;

import io.github.hmnshgpt455.common.request.SignUpRequest;
import io.github.hmnshgpt455.common.responses.SignUpResponse;

public interface UserAuthenticationService {

    SignUpResponse signUp(SignUpRequest signUpRequest);
}
