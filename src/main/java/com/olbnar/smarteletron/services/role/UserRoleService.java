package com.olbnar.smarteletron.services.role;

import com.olbnar.smarteletron.dtos.role.UserRolesRequest;
import com.olbnar.smarteletron.dtos.role.UserRolesResponse;

public interface UserRoleService {
    UserRolesResponse saveOrUpdateUserRoles(UserRolesRequest userRolesRequest);

    UserRolesResponse getUserRoles(Long id);
}
