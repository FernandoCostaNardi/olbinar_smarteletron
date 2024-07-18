package com.olbnar.smarteletron.models.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Builder.Default
    private  Set<RoleModel> roles = new HashSet<>();

    private String authorities;

    @Column(name = "isactive", columnDefinition = "boolean default true")
    private boolean isActive;

    @Column(name = "isnotlocked", columnDefinition = "boolean default true")
    private boolean isNotLocked;

}
