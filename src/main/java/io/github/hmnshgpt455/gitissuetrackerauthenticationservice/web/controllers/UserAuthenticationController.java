package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.web.controllers;

import io.github.hmnshgpt455.common.model.UserAuthenticationDTO;
import io.github.hmnshgpt455.common.responses.AvailabilityResponse;
import io.github.hmnshgpt455.common.responses.SignUpResponse;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.services.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(UserAuthenticationController.BASE_PATH_V1_AUTHENTICATION)
public class UserAuthenticationController {
    public static final String BASE_PATH_V1_AUTHENTICATION = "/api/v1/authentication";

    private final UserAuthenticationService userAuthenticationService;

    @PostMapping(path = "/signUp", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public SignUpResponse signUpViaPayload(@Valid @RequestBody UserAuthenticationDTO userAuthenticationDTO) {
        return userAuthenticationService.signUp(userAuthenticationDTO);
    }

    @PostMapping(path = "/signUp", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public SignUpResponse signUpViaForm(@Valid UserAuthenticationDTO userAuthenticationDTO) {
        return userAuthenticationService.signUp(userAuthenticationDTO);
    }

    @GetMapping("/username/{username}/availability")
    @ResponseBody
    public AvailabilityResponse validateAvailability(@PathVariable("username") String username) {
        return userAuthenticationService.checkUsernameAvailability(username);
    }
}
