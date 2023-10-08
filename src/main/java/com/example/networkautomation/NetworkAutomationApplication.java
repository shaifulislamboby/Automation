package com.example.networkautomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//newly added
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NetworkAutomationApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(NetworkAutomationApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
