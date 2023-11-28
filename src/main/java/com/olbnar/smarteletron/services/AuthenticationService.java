package com.olbnar.smarteletron.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.olbnar.smarteletron.records.security.JwtRecord;
import com.olbnar.smarteletron.records.security.LoginRecord;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthenticationService {

    ResponseEntity<JwtRecord> authenticateUser(@Valid @RequestBody LoginRecord loginDto) throws JsonProcessingException;

}
