package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.web.controllers;

import io.github.hmnshgpt455.common.request.SignUpRequest;
import io.github.hmnshgpt455.common.responses.SignUpResponse;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.services.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(UserAuthenticationController.BASE_PATH_V1_AUTHENTICATION)
public class UserAuthenticationController {
    public static final String BASE_PATH_V1_AUTHENTICATION = "/api/v1/authentication";

    private final UserAuthenticationService userAuthenticationService;

    @PostMapping("/signUp")
    public SignUpResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return userAuthenticationService.signUp(signUpRequest);
    }
}
