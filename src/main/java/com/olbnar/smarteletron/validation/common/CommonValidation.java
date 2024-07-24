package com.olbnar.smarteletron.validation.common;

import com.olbnar.smarteletron.dtos.role.UserRolesRequest;
import com.olbnar.smarteletron.exception.user.UserNotFoundException;
import com.olbnar.smarteletron.models.authentication.UserModel;
import com.olbnar.smarteletron.repositories.authentication.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonValidation {

    @Autowired
    private UserModelRepository userRepository;

    public UserModel getUserModel(UserRolesRequest userRolesRequest) {
        UserModel user = userRepository.findById(userRolesRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        return user;
    }

    public UserModel getUserModel(Long userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        return user;
    }
}
