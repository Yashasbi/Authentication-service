package com.authorizationservice.service;

import com.authorizationservice.model.entities.Role;
import com.authorizationservice.repository.RoleRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void createRoles(Set<Role> roles) {

        roleRepository.saveAllAndFlush(roles);
    }

    public Role getRole(@NonNull Integer id) {

        return roleRepository.getReferenceById(id);
    }
}
