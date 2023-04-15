package com.olbnar.smarteletron.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authe")
public class AuthenticationController {

    @GetMapping("/signIn")
    public String signIn() {
        return "Logou direito";
    }
}
