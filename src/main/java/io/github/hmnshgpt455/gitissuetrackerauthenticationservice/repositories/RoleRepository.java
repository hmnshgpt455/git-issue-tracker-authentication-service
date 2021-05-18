package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.repositories;

import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Set<Role> findAllByNameIn(Set<String> roleNames);
}
