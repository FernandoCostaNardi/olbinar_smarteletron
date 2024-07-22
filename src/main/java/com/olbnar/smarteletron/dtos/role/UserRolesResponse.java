package com.olbnar.smarteletron.dtos.role;

import com.olbnar.smarteletron.models.authentication.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesResponse {

    private Long UserId;

    private String UserName;

    private List<RoleModel> Roles;

}
