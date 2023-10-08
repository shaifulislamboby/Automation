package com.example.networkautomation.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomePage {

    @Value("${welcomeMessage}")
    private String message;

    @GetMapping("/")
    public String index(){
        return message;
    }
}
