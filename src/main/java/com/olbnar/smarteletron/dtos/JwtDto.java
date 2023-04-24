package com.olbnar.smarteletron.dtos;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class JwtDto {

    @NonNull
    private String token;
    private String username;
    private String name;
    private LocalDateTime lastLoginDate;
    private String lastLoginDateDisplay;
    private LocalDateTime joinDate;
    private String[] authorities;
    private boolean isActive;
    private boolean isNotLocked;
    private String type = "Bearer";
}
