package com.example.networkautomation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Regular user Dto */
public class RegularUserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String password;

    private String lastLogin;

    private SuperUserDto createdBy;

    private String createdOn;
    private String role;

    public RegularUserDto() {}

    /** parameterize constructor */
    public RegularUserDto(
            Long id,
            String firstName,
            String lastName,
            String userName,
            String email,
            String password,
            String lastLogin,
            SuperUserDto createdBy,
            String createdOn,
            String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.role = role;
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
        return getFormattedCreatedOn();
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = getFormattedCreatedOn();
    }

    public SuperUserDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(SuperUserDto createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFormattedCreatedOn() {
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(dateformat);
    }
}
