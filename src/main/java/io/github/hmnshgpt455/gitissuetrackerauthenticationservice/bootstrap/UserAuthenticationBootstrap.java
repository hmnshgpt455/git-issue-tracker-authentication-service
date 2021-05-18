package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.bootstrap;

import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.domain.Role;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.domain.UserAuthentication;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.repositories.RoleRepository;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.repositories.UserAuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserAuthenticationBootstrap implements CommandLineRunner {

    private final UserAuthenticationRepository userAuthenticationRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.findAll().isEmpty() && userAuthenticationRepository.findAll().isEmpty()) {
            Role role1 = roleRepository.save(Role.builder()
                    .name("PMO")
                    .build());
            Role role2 = roleRepository.save(Role.builder()
                    .name("ADMIN")
                    .build());

            Role role3 = roleRepository.save(Role.builder()
                    .name("DEVELOPER")
                    .build());

            Role role4 = roleRepository.save(Role.builder()
                    .name("PO")
                    .build());

            Set<Role> roleGroup1 = new HashSet<>();
            roleGroup1.add(role1);
            roleGroup1.add(role2);

            Set<Role> roleGroup2 = new HashSet<>();
            roleGroup2.add(role4);
            roleGroup2.add(role2);
            roleGroup2.add(role3);

//            UserAuthentication userAuthentication1 = UserAuthentication.builder()
//                    .password("pwd1")
//                    .roles(roleGroup1)
//                    .username("hmnshgpt455")
//                    .email("ss@ss.com")
//                    .build();
//            UserAuthentication userAuthentication2 = UserAuthentication.builder()
//                    .password("pwd2")
//                    .roles(roleGroup2)
//                    .username("hmnshgpt456")
//                    .email("ss@dd.com")
//                    .build();
//
//            userAuthenticationRepository.save(userAuthentication1);
//
//            userAuthenticationRepository.save(userAuthentication2);
        }
    }
}
