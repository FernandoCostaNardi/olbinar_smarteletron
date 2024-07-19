package com.olbnar.smarteletron.services.user.impl;

import com.olbnar.smarteletron.dtos.user.UserListResponse;
import com.olbnar.smarteletron.dtos.user.UserRequest;
import com.olbnar.smarteletron.dtos.user.UserResponse;
import com.olbnar.smarteletron.exception.user.UserNotFoundException;
import com.olbnar.smarteletron.mapper.user.UserMapper;
import com.olbnar.smarteletron.models.authentication.UserModel;
import com.olbnar.smarteletron.repositories.authentication.UserModelRepository;
import com.olbnar.smarteletron.services.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserModelRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserModelRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse saveOrUpdateUser(UserRequest userRequest) {
        log.info("Iniciando o processo de cadastro ou atualização de usuário");
        var userModel = userMapper.requestToModel(userRequest);
        userRepository.save(userModel);
        log.info("Finalizado o processo de cadastro ou atualização de usuário");
        return userMapper.modelToResponse(userModel);
    }

    @Override
    public Page<UserListResponse> listUsers(Pageable pageable, String name) {
        Page<UserModel> userModelPage;
        if (name != null && !name.isEmpty()) {
            userModelPage = userRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            userModelPage = userRepository.findAll(pageable);
        }
        return userModelPage.map(userMapper::modelToListResponse);
    }

    @Override
    public UserResponse getUserById(Long id) {
        var userModel = userRepository.findById(id);
        if (userModel.isPresent()) {
            return userMapper.modelToResponse(userModel.get());
        } else {
            throw new UserNotFoundException("Não existe usuário com o id informado: " + id);
        }
    }
}
