package com.olbnar.smarteletron.services.user.impl;

import com.olbnar.smarteletron.dtos.user.UserRequest;
import com.olbnar.smarteletron.dtos.user.UserResponse;
import com.olbnar.smarteletron.mapper.user.UserMapper;
import com.olbnar.smarteletron.repositories.authentication.UserModelRepository;
import com.olbnar.smarteletron.services.user.UserService;
import lombok.extern.log4j.Log4j2;
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
}
