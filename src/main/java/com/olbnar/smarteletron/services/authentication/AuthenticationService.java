package com.olbnar.smarteletron.services.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.olbnar.smarteletron.dtos.authentication.JwtDto;
import com.olbnar.smarteletron.dtos.authentication.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthenticationService {

    ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) throws JsonProcessingException;

    void updateLastLoginDate(LoginDto loginDto);

}
