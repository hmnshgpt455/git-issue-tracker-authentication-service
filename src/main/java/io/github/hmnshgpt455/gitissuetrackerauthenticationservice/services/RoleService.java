package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.services;

import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.domain.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> findPersistedRoleEntitiesByNames(Set<String> roleNames);
}
