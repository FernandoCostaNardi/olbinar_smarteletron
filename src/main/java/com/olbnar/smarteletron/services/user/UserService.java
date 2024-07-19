package com.olbnar.smarteletron.services.user;

import com.olbnar.smarteletron.dtos.user.UserListResponse;
import com.olbnar.smarteletron.dtos.user.UserRequest;
import com.olbnar.smarteletron.dtos.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse saveOrUpdateUser(UserRequest userRequest);

    public Page<UserListResponse> listUsers(Pageable pageable, String name);

    UserResponse getUserById(Long id);
}
