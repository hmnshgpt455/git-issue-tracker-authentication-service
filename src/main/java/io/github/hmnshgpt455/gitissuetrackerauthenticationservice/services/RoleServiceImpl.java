package io.github.hmnshgpt455.gitissuetrackerauthenticationservice.services;

import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.domain.Role;
import io.github.hmnshgpt455.gitissuetrackerauthenticationservice.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Set<Role> findPersistedRoleEntitiesByNames(Set<String> roleNames) {
        return roleRepository.findAllByNameIn(roleNames);
    }
}
