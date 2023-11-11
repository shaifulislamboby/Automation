package com.example.networkautomation.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a Javadoc comment. It provides information about the method or class.
 * You can include a description, parameter explanations, return value, exceptions, etc.
 */
@RestController
public class HomePage {

    @Value("${welcomeMessage}")
    private String message;

    @GetMapping("/hello")
    public String index(Model model){
        model.addAttribute("message", message);
        return "hello";
    @GetMapping("/")
    public String index() {
        return message;
    }
}
