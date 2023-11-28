package com.olbnar.smarteletron.models.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olbnar.smarteletron.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class RoleModel implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 30)
    private RoleType roleName;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.roleName.toString();
    }

}
