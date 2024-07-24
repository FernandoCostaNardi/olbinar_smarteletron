package com.olbnar.smarteletron.services.role.impl;

import com.olbnar.smarteletron.dtos.role.UserRolesRequest;
import com.olbnar.smarteletron.dtos.role.UserRolesResponse;
import com.olbnar.smarteletron.exception.role.RoleNotFoundException;
import com.olbnar.smarteletron.models.authentication.RoleModel;
import com.olbnar.smarteletron.models.authentication.UserModel;
import com.olbnar.smarteletron.models.role.UserRoles;
import com.olbnar.smarteletron.repositories.authentication.RoleRepository;
import com.olbnar.smarteletron.repositories.role.UserRolesRepository;
import com.olbnar.smarteletron.services.role.UserRoleService;
import com.olbnar.smarteletron.validation.common.CommonValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private CommonValidation commonValidation;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Override
    @Transactional
    public UserRolesResponse saveOrUpdateUserRoles(UserRolesRequest userRolesRequest) {

        UserModel user = commonValidation.getUserModel(userRolesRequest);

        var requestedRoles = validateRoles(userRolesRequest);

        List<RoleModel> currentRoles = new ArrayList<>(user.getRoles());

        saveRoles(user, requestedRoles);

        removeRoles(user, requestedRoles, currentRoles);

        List<RoleModel> rolesByUserId = roleRepository.findRolesByUserId(user.getId());

        return new UserRolesResponse(user.getId(), user.getName(), rolesByUserId);
    }

    @Override
    public UserRolesResponse getUserRoles(Long id) {
        UserModel user = commonValidation.getUserModel(id);
        List<RoleModel> rolesByUserId = roleRepository.findRolesByUserId(user.getId());
        return new UserRolesResponse(user.getId(), user.getName(), rolesByUserId);
    }

    private void removeRoles(UserModel user, List<RoleModel> requestedRoles, List<RoleModel> currentRoles) {
        currentRoles.forEach(role -> {
            if (!requestedRoles.contains(role)) {
                userRolesRepository.deleteByUserIdAndRoleId(user.getId(), role.getRoleId());
            }
        });
    }

    private void saveRoles(UserModel user, List<RoleModel> requestedRoles) {
        requestedRoles.forEach(role -> {
            if (!user.getRoles().contains(role)) {
                userRolesRepository.save(new UserRoles(user.getId(), role.getRoleId()));
            }
        });
    }

    private List<RoleModel> validateRoles(UserRolesRequest userRolesRequest) {
        List<RoleModel> roles = new ArrayList<>();
        userRolesRequest.getRoles().forEach(role -> {
           RoleModel roleModel = roleRepository.findByRoleId(role.getRoleId()).orElseThrow( () -> new RoleNotFoundException("Role not found"));
            roles.add(roleModel);
        });
        return roles;
    }


}
