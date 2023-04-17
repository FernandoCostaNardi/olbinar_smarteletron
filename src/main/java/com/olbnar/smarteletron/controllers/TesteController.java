package com.olbnar.smarteletron.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TesteController {

    @GetMapping("/signIn")
    public String testa() {
        return "Executou somente com o JWT Token aeee";
    }
}
