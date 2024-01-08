package com.example.networkautomation.service;

import com.example.networkautomation.entity.UserInfo;
import com.example.networkautomation.repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImp implements SignupService{
    @Autowired
    SignupRepository signupRepository;
    public void saveUserInfo(UserInfo userInfo) {
        signupRepository.save(userInfo);
    }
}
