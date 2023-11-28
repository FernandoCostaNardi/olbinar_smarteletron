package com.olbnar.smarteletron.models.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class PersonModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "profileimageurl")
    private String profileImageUrl;

    @Column(name = "lastlogindate")
    private LocalDateTime lastLoginDate;

    @Column(name = "lastlogindatedisplay")
    private LocalDateTime lastLoginDateDisplay;

    @Column(name = "joindate")
    private LocalDateTime joinDate;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "isactive")
    private boolean isActive;

    @Column(name = "isnotlocked")
    private boolean isNotLocked;

}