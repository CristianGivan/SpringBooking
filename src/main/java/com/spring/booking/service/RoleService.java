package com.spring.booking.service;

import com.spring.booking.model.Role;
import com.spring.booking.model.RoleType;
import com.spring.booking.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByType(RoleType roleType){
        return roleRepository.findRoleByRoleType(roleType);
    }
}
