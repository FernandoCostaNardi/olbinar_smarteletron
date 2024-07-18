package com.olbnar.smarteletron.services.authentication;

import com.olbnar.smarteletron.enums.RoleType;
import com.olbnar.smarteletron.models.authentication.RoleModel;

import java.util.Optional;

public interface RoleService {

    Optional<RoleModel> findByRoleName(RoleType roleType);
}
