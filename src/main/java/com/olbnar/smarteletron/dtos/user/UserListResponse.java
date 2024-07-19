package com.olbnar.smarteletron.dtos.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data


public class UserListResponse {

    private Long id;

    private String username;

    private String name;

    private String profileImageUrl;

    private LocalDateTime joinDate;

    private boolean isActive  = true;

    private boolean isNotLocked = true;

}
