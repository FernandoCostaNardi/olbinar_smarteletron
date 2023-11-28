package com.olbnar.smarteletron.services.security.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.olbnar.smarteletron.configs.security.JwtProvider;
import com.olbnar.smarteletron.records.security.JwtRecord;
import com.olbnar.smarteletron.records.security.LoginRecord;
import com.olbnar.smarteletron.repositories.security.UserModelRepository;
import com.olbnar.smarteletron.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserModelRepository userModelRepository;

    @Override
    public ResponseEntity<JwtRecord> authenticateUser(LoginRecord loginDto) throws JsonProcessingException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwt(authentication);
        return ResponseEntity.ok(new JwtRecord(jwt));
    }
}
