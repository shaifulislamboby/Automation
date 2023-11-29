package com.example.networkautomation.routes;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * This is a Javadoc comment. It provides information about the method or class.
 * You can include a description, parameter explanations, return value, exceptions, etc.
 */
@Configuration
@EnableWebMvc
public class PathsConfig implements WebMvcConfigurer {
    /**
     * This is a Javadoc comment. It provides information about the method or class.
     * You can include a description, parameter explanations, return value, exceptions, etc.
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }

}



