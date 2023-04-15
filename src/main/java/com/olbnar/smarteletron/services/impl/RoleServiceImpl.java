package com.olbnar.smarteletron.services.impl;

import com.olbnar.smarteletron.enums.RoleType;
import com.olbnar.smarteletron.models.RoleModel;
import com.olbnar.smarteletron.repositories.RoleRepository;
import com.olbnar.smarteletron.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<RoleModel> findByRoleName(RoleType roleType) {
        return roleRepository.findByRoleName(roleType


















































































































































































































































        );
    }
}
