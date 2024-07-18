package com.olbnar.smarteletron.services.authentication.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.olbnar.smarteletron.configs.security.JwtProvider;
import com.olbnar.smarteletron.dtos.authentication.JwtDto;
import com.olbnar.smarteletron.dtos.authentication.LoginDto;
import com.olbnar.smarteletron.models.authentication.UserModel;
import com.olbnar.smarteletron.repositories.authentication.UserModelRepository;
import com.olbnar.smarteletron.services.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserModelRepository userModelRepository;

    @Override
    public ResponseEntity<JwtDto> authenticateUser(LoginDto loginDto) throws JsonProcessingException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwt(authentication);
        updateLastLoginDate(loginDto);
        return ResponseEntity.ok(new JwtDto(jwt));
    }

    @Override
    public void updateLastLoginDate(LoginDto loginDto) {
        var userModal = userModelRepository.findByUsername(loginDto.getUsername()).get();
        LocalDateTime lastLoginDate = LocalDateTime.now();

        if(userModal.getLastLoginDateDisplay() != null) {
            lastLoginDate = getLocalDateTime(userModal);
        }

        userModal.setLastLoginDate(lastLoginDate);
        userModal.setLastLoginDateDisplay(LocalDateTime.now().toString());
        userModelRepository.save(userModal);
    }

    private static LocalDateTime getLocalDateTime(UserModel userModal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(userModal.getLastLoginDateDisplay(), formatter);
    }
}
