package com.example.networkautomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is a Javadoc comment. It provides information about the method or class.
 * You can include a description, parameter explanations, return value, exceptions, etc.
 */
@SpringBootApplication
public class NetworkAutomationApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(NetworkAutomationApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
