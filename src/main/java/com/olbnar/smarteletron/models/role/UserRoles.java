package com.olbnar.smarteletron.models.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_roles")
@IdClass(UserRoleId.class)
public class UserRoles {

    @Id
    @Column(name="user_id", nullable = false, length = 30)
    private Long userId;

    @Id
    @Column(name="role_id", nullable = false, length = 30)
    private Long roleId;
}
