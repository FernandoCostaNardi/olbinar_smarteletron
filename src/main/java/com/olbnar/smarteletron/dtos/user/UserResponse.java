package com.olbnar.smarteletron.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String username;

    private String name;

    private String profileImageUrl;

    private LocalDateTime lastLoginDate;

    private String lastLoginDateDisplay;

    private LocalDateTime joinDate;

    private boolean isActive;

    private boolean isNotLocked;

}
