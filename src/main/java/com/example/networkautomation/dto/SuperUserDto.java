package com.example.networkautomation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/** Super user Dto */
@Component
public class SuperUserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String lastLogin;
    private String createdBy;
    private String createdOn;
    private String role;

    public SuperUserDto() {}

    /** Parameterize constructor */
    public SuperUserDto(
            Long id,
            String firstName,
            String lastName,
            String userName,
            String email,
            String password,
            String lastLogin,
            String createdBy,
            String createdOn,
            String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.lastLogin = getFormattedCreatedOn();
        this.createdBy = firstName + " " + lastName;
        this.createdOn = getFormattedCreatedOn();
        this.role = role;
    }

    public String getFormattedCreatedOn() {
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(dateformat);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getIsSuperUser() {
        return role;
    }

    public void setIsSuperUser(String role) {
        this.role = role;
    }
}
