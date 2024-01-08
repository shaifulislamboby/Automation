package com.example.networkautomation.controllers;

import com.example.networkautomation.entity.UserInfo;
import com.example.networkautomation.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpInController {
    @Autowired
    SignupService signupService;

    @GetMapping("/signin")
    public String showSignInPage() {
        return "signin";
    }
    @GetMapping("/signup")
    public String showSignUpPage(Model model) {
       model.addAttribute("userInfo", new UserInfo());
        return "signup";
    }

    @PostMapping("/newuser")
    public String userInfoAdded(@ModelAttribute("userInfo") UserInfo userInfo, Model model) {
        model.addAttribute("userinfo", userInfo);
        //signupService.saveUserInfo(userInfo);
        return "signupsuccess";
    }
}
