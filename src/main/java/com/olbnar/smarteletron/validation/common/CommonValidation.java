package com.olbnar.smarteletron.validation.common;

import com.olbnar.smarteletron.dtos.role.UserRolesRequest;
import com.olbnar.smarteletron.exception.user.UserNotFoundException;
import com.olbnar.smarteletron.models.authentication.UserModel;
import com.olbnar.smarteletron.repositories.authentication.UserModelRepository;
import org.springframework.stereotype.Component;

@Component
public class CommonValidation {

    private final UserModelRepository userRepository;

    public CommonValidation(UserModelRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel getUserModel(UserRolesRequest userRolesRequest) {
        return userRepository.findById(userRolesRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

    public UserModel getUserModel(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }
}
