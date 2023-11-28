package com.olbnar.smarteletron.repositories.security;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.olbnar.smarteletron.models.security.UserModel;

import java.util.Optional;
import java.util.UUID;

public interface UserModelRepository extends JpaRepository<UserModel, UUID> {

    @EntityGraph(attributePaths = "roles", type = EntityGraph.EntityGraphType.FETCH)
    Optional<UserModel> findByUsername(String username);

    @EntityGraph(attributePaths = "roles", type = EntityGraph.EntityGraphType.FETCH)
   Optional<UserModel> findById(UUID userId);

}
