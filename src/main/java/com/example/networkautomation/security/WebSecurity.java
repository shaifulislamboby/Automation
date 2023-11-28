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

/** We are enabling spring boot web security which is going to store a user. */
@Configuration
@EnableWebSecurity
public class WebSecurity {
    /** Creating a Bean which going to tell the permit and authentication endpoints. */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        (request) ->
                                request.requestMatchers("/", "/home")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated())
                .formLogin(
                        (form) -> form.loginPage("/login").defaultSuccessUrl("/hello").permitAll())
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    /** We are creating a user which going to store in-memory */
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
