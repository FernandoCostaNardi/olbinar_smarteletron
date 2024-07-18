package com.olbnar.smarteletron.mapper.user;

import com.olbnar.smarteletron.dtos.user.UserRequest;
import com.olbnar.smarteletron.dtos.user.UserResponse;
import com.olbnar.smarteletron.models.authentication.UserModel;
import com.olbnar.smarteletron.repositories.authentication.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserModelRepository userRepository;

    @Autowired
    public UserMapper(BCryptPasswordEncoder passwordEncoder, UserModelRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public UserModel requestToModel(UserRequest userRequest) {

        var userModel = new UserModel();
        if(userRequest.getId() != null){
            // buscar do repositorio o usermodel
            userModel = userRepository.findById(userRequest.getId()).get();
        }

        userModel.setUsername(userRequest.getUsername());
        if (userRequest.getPassword() != null) {
            userModel.setPassword(passwordEncoder.encode(userRequest.getPassword())); // Encripta a senha
        }
        userModel.setName(userRequest.getName());
        if (userRequest.getProfileImageUrl() == null) {
            // se o profileImageUrl for nulo no usermodel, seta um valor padrão
            if(userModel.getProfileImageUrl() == null) {
                userModel.setProfileImageUrl("https://www.pngitem.com/pimgs/m/146-1468479_my-profile-icon-blank-profile-picture-circle-hd.png");
            }
        } else {
            userModel.setProfileImageUrl(userRequest.getProfileImageUrl());
        }
        //Se o id do userRequest não for nulo, deve usar o valor do userModel se não deve deixar o valor vazio
        userModel.setLastLoginDate(userRequest.getId() != null ? userModel.getLastLoginDate() : null);
        userModel.setLastLoginDateDisplay(userRequest.getId() != null ? userModel.getLastLoginDateDisplay() : null);
        userModel.setJoinDate(userRequest.getId() != null ? userModel.getJoinDate() : LocalDateTime.now());
        userModel.setActive(userRequest.isActive());
        userModel.setNotLocked(userRequest.isNotLocked());
        return userModel;
    }

    public UserResponse modelToResponse(UserModel userModel){

        var userResponse = new UserResponse();

        userResponse.setId(userModel.getId());
        userResponse.setUsername(userModel.getUsername());
        userResponse.setName(userModel.getName());
        userResponse.setProfileImageUrl(userModel.getProfileImageUrl());
        userResponse.setLastLoginDate(userModel.getLastLoginDate());
        userResponse.setLastLoginDateDisplay(userModel.getLastLoginDateDisplay());
        userResponse.setJoinDate(userModel.getJoinDate());
        userResponse.setActive(userModel.isActive());
        userResponse.setNotLocked(userModel.isNotLocked());
        return userResponse;
    }
}
