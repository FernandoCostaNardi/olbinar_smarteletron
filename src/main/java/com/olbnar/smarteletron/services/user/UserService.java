package com.olbnar.smarteletron.services.user;

import com.olbnar.smarteletron.dtos.user.UserRequest;
import com.olbnar.smarteletron.dtos.user.UserResponse;

public interface UserService {

    UserResponse saveOrUpdateUser(UserRequest userRequest);
}
