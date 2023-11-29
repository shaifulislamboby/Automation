package com.example.networkautomation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


/**
 * This is a Javadoc comment. It provides information about the method or class.
 * You can include a description, parameter explanations, return value, exceptions, etc.
 */
@Configuration
@EnableWebSecurity
public class WebSecurity {


    /**
     * This is a Javadoc comment. It provides information about the method or class.
     * You can include a description, parameter explanations, return value, exceptions, etc.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/", "/home").permitAll()
                        .anyRequest().permitAll())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/hello")
                        .permitAll())
                .logout((logout) -> logout
                        .permitAll());

        return http.build();
    }

    /**
     * This is a Javadoc comment. It provides information about the method or class.
     * You can include a description, parameter explanations, return value, exceptions, etc.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("user")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }
}
