package com.olbnar.smarteletron.dtos.role;

import com.olbnar.smarteletron.models.authentication.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesRequest {

    private Long UserId;

    private List<RoleModel> roles;

    public void removeRole(RoleModel role) {
        roles.remove(role);
    }
}
