package com.olbnar.smarteletron.services.user.impl;

import com.olbnar.smarteletron.dtos.user.UserListResponse;
import com.olbnar.smarteletron.dtos.user.UserRequest;
import com.olbnar.smarteletron.dtos.user.UserResponse;
import com.olbnar.smarteletron.exception.user.UserNotFoundException;
import com.olbnar.smarteletron.mapper.user.UserMapper;
import com.olbnar.smarteletron.models.authentication.UserModel;
import com.olbnar.smarteletron.repositories.authentication.UserModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserModelRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrUpdateUser_createsNewUser_whenUserDoesNotExist() {
        UserRequest userRequest = new UserRequest(); // Populate with test data
        UserModel userModel = new UserModel(); // Populate with expected data
        UserResponse expectedResponse = new UserResponse(); // Populate with expected data

        when(userMapper.requestToModel(userRequest)).thenReturn(userModel);
        when(userRepository.save(userModel)).thenReturn(userModel);
        when(userMapper.modelToResponse(userModel)).thenReturn(expectedResponse);

        UserResponse actualResponse = userService.saveOrUpdateUser(userRequest);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(userRepository, times(1)).save(userModel);
    }

    @Test
    void saveOrUpdateUser_updatesExistingUser_whenUserExists() {
        UserRequest userRequest = new UserRequest(); // Assume this request is for an existing user
        UserModel updatedUserModel = new UserModel(); // Updated user model
        UserResponse expectedResponse = new UserResponse(); // Expected response after update

        when(userMapper.requestToModel(userRequest)).thenReturn(updatedUserModel);
        when(userRepository.save(updatedUserModel)).thenReturn(updatedUserModel);
        when(userMapper.modelToResponse(updatedUserModel)).thenReturn(expectedResponse);

        UserResponse actualResponse = userService.saveOrUpdateUser(userRequest);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(userRepository, times(1)).save(updatedUserModel);
    }

    @Test
    void listUsers_returnsEmptyPage_whenNoUsersExist() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserModel> emptyPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(userRepository.findAll(pageable)).thenReturn(emptyPage);

        Page<UserListResponse> result = userService.listUsers(pageable, null);

        assertEquals(0, result.getTotalElements());
    }

    @Test
    void listUsers_returnsUsers_whenUsersExist() {
        Pageable pageable = PageRequest.of(0, 10);
        UserModel userModel = new UserModel(); // Populate with test data
        List<UserModel> userList = Collections.singletonList(userModel);
        Page<UserModel> userPage = new PageImpl<>(userList, pageable, userList.size());
        UserListResponse userListResponse = new UserListResponse(); // Populate with expected data

        when(userRepository.findAll(pageable)).thenReturn(userPage);
        when(userMapper.modelToListResponse(userModel)).thenReturn(userListResponse);

        Page<UserListResponse> result = userService.listUsers(pageable, null);

        assertEquals(1, result.getTotalElements());
        assertEquals(userListResponse, result.getContent().get(0));
    }

    @Test
    void listUsers_filtersByName_whenNameIsProvided() {
        Pageable pageable = PageRequest.of(0, 10);
        String name = "Test Name";
        UserModel userModel = new UserModel(); // Populate with test data
        List<UserModel> userList = Collections.singletonList(userModel);
        Page<UserModel> userPage = new PageImpl<>(userList, pageable, userList.size());
        UserListResponse userListResponse = new UserListResponse(); // Populate with expected data

        when(userRepository.findByNameContainingIgnoreCase(name, pageable)).thenReturn(userPage);
        when(userMapper.modelToListResponse(userModel)).thenReturn(userListResponse);

        Page<UserListResponse> result = userService.listUsers(pageable, name);

        assertEquals(1, result.getTotalElements());
        assertEquals(userListResponse, result.getContent().get(0));
    }


    @Test
    void getUserById_returnsUserResponse_whenUserExists() {
        Long userId = 1L;
        UserModel userModel = new UserModel(); // Assume this is populated with test data
        UserResponse expectedResponse = new UserResponse(); // Assume this is populated with expected data

        when(userRepository.findById(userId)).thenReturn(Optional.of(userModel));
        when(userMapper.modelToResponse(userModel)).thenReturn(expectedResponse);

        UserResponse actualResponse = userService.getUserById(userId);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getUserById_throwsUserNotFoundException_whenUserDoesNotExist() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }
}