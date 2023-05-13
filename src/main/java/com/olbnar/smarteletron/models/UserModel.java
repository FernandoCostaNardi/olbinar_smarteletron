package com.olbnar.smarteletron.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String username;

    private String password;

    private String name;

    @Column(name = "profileimageurl")
    private String profileImageUrl;

    @Column(name = "lastlogindate")
    private LocalDateTime lastLoginDate;

    @Column(name = "lastlogindatedisplay")
    private String lastLoginDateDisplay;

    @Column(name = "joindate")
    private LocalDateTime joinDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleModel> roles = new HashSet<>();

    private String authorities;

    @Column(name = "isactive")
    private boolean isActive;

    @Column(name = "isnotlocked")
    private boolean isNotLocked;

}
