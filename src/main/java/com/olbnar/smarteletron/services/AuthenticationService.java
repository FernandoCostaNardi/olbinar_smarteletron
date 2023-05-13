package com.olbnar.smarteletron.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.olbnar.smarteletron.dtos.JwtDto;
import com.olbnar.smarteletron.dtos.LoginDto;
import com.olbnar.smarteletron.models.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthenticationService {

    ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) throws JsonProcessingException;

}
