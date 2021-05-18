package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.repositories;

import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.domain.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, UUID> {
    boolean existsByUsername(String userName);
    boolean existsByEmail(String email);
}
