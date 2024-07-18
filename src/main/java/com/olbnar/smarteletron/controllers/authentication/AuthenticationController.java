package com.olbnar.smarteletron.controllers.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.olbnar.smarteletron.dtos.authentication.JwtDto;
import com.olbnar.smarteletron.dtos.authentication.LoginDto;
import com.olbnar.smarteletron.services.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) throws JsonProcessingException {
        return authenticationService.authenticateUser(loginDto);
    }



}
