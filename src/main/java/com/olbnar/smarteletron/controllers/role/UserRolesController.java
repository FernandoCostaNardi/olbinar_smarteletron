package com.olbnar.smarteletron.controllers.role;

import com.olbnar.smarteletron.dtos.role.UserRolesRequest;
import com.olbnar.smarteletron.dtos.role.UserRolesResponse;
import com.olbnar.smarteletron.services.role.UserRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user-roles")
public class UserRolesController {

    private final UserRoleService userRoleService;

    public UserRolesController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping("/save")
    public UserRolesResponse saveOrUpdateUserRoles(@RequestBody UserRolesRequest userRolesRequest) {
        return userRoleService.saveOrUpdateUserRoles(userRolesRequest);
    }

    //Criar uma api para buscar todos os roles de um usuário através do id do usuário e retornar uma lista de RoleModel. Lembrnado que o usuario tem que ter role de ADMIM ou MANAGER para acessar essa api.
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/{id}")
    public UserRolesResponse getUserRoles(@PathVariable Long id) {
        return userRoleService.getUserRoles(id);
    }

}
