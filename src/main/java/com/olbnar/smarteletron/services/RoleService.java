package com.olbnar.smarteletron.services;

import com.olbnar.smarteletron.enums.RoleType;
import com.olbnar.smarteletron.models.RoleModel;

import java.util.Optional;

public interface RoleService {

    Optional<RoleModel> findByRoleName(RoleType roleType);
}
