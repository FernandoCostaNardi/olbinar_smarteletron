package com.olbnar.smarteletron.repositories.role;

import com.olbnar.smarteletron.models.role.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    void deleteByUserIdAndRoleId(Long userId, Long roleId);
}
