package com.example.networkautomation.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This is a Javadoc comment. It provides information about the method or class. You can include a
 * description, parameter explanations, return value, exceptions, etc.
 */
@Controller
public class HomePage {

    @Value("${welcomeMessage}")
    private String message;

    /** Creating a handler method which going to handle / endpoint */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", message);
        return "home";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("message", message);
        return "home";
    }
}
