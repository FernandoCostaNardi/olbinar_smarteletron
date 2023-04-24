package com.olbnar.smarteletron.configs.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olbnar.smarteletron.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private UUID userId;
    private String username;
    @JsonIgnore
    private String password;
    private String name;
    private LocalDateTime lastLoginDate;
    private String lastLoginDateDisplay;
    private LocalDateTime joinDate;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isActive;
    private boolean isNotLocked;


    public static UserDetailsImpl build(UserModel userModel) {
        List<GrantedAuthority> authorities = userModel.getRoles().stream()
                .map(roleModel -> new SimpleGrantedAuthority(roleModel.getAuthority()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getPassword(),
                userModel.getName(),
                userModel.getLastLoginDate(),
                userModel.getLastLoginDateDisplay(),
                userModel.getJoinDate(),
                authorities,
                userModel.isActive(),
                userModel.isNotLocked());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
