package com.olbnar.smarteletron.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long id;

    @NotBlank
    @Email
    private String username;

    private String password;

    @NotBlank
    private String name;

    private String profileImageUrl;

    private LocalDateTime lastLoginDate;

    private String lastLoginDateDisplay;

    private LocalDateTime joinDate;

    private String authorities;

    private boolean isActive  = true;

    private boolean isNotLocked = true;

}
