package com.example.networkautomation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/** Security configuration */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /** Selecting allowed endpoints */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                configurer ->
                        configurer
                                .requestMatchers(HttpMethod.POST, "/automation/superuser/**")
                                .hasRole("SUPER")
                                .requestMatchers(HttpMethod.GET, "/automation/superuser/**")
                                .hasRole("SUPER")
                                .requestMatchers(HttpMethod.PUT, "/automation/superuser/**")
                                .hasRole("SUPER")
                                .requestMatchers(HttpMethod.PATCH, "/automation/superuser/**")
                                .hasRole("SUPER")
                                .requestMatchers(HttpMethod.DELETE, "/automation/superuser/**")
                                .hasRole("SUPER")
                                .requestMatchers(HttpMethod.GET, "/user/**")
                                .hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/user/**")
                                .hasRole("USER")
                                .requestMatchers(HttpMethod.PATCH, "/user/**")
                                .hasRole("USER"));

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
