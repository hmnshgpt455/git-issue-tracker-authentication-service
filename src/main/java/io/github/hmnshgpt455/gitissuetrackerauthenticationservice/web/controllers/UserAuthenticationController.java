package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(UserAuthenticationController.BASE_PATH_V1_AUTHENTICATION)
public class UserAuthenticationController {
    public static final String BASE_PATH_V1_AUTHENTICATION = "/api/v1/authentication";

}
