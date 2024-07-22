
package com.olbnar.smarteletron.repositories.authentication;

import com.olbnar.smarteletron.enums.RoleType;
import com.olbnar.smarteletron.models.authentication.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    Optional<RoleModel> findByRoleName(RoleType name);

    @Query("SELECT r FROM RoleModel r JOIN UserRoles ur ON r.roleId = ur.roleId WHERE ur.userId = :userId")
    List<RoleModel> findRolesByUserId(@Param("userId") Long userId);

    Optional<RoleModel> findByRoleId(Long roleId);


}
