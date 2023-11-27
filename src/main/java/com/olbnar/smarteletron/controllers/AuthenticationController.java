package com.olbnar.smarteletron.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.olbnar.smarteletron.records.JwtRecord;
import com.olbnar.smarteletron.records.LoginRecord;
import com.olbnar.smarteletron.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/signIn")
    public String signIn() {
        return "Logou direito";
    }

    @PostMapping("/login")
    public ResponseEntity<JwtRecord> authenticateUser(@Valid @RequestBody LoginRecord loginDto) throws JsonProcessingException {
        return authenticationService.authenticateUser(loginDto);
    }



}
