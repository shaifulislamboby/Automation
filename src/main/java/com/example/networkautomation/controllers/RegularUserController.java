package com.example.networkautomation.controllers;

import com.example.networkautomation.dto.RegularUserDto;
import com.example.networkautomation.service.RegularUserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Create a regular user controller */
@RestController
@RequestMapping("/user")
public class RegularUserController {

    private RegularUserService regularUserService;

    @Autowired
    public RegularUserController(RegularUserService regularUserService) {
        this.regularUserService = regularUserService;
    }

    /** Get a regular user info */
    @GetMapping("/regularuser/{id}")
    public ResponseEntity<RegularUserDto> getRegularUser(@PathVariable Long id) {
        return new ResponseEntity<>(regularUserService.getRegularUserInfo(id), HttpStatus.OK);
    }

    /** Update a specific regular user */
    @PutMapping("/updateregularuser/{id}")
    public ResponseEntity<RegularUserDto> updateRegularUser(
            @PathVariable Long id, @RequestBody RegularUserDto regularUserDto) {
        return new ResponseEntity<>(
                regularUserService.updateRegularUser(regularUserDto, id), HttpStatus.OK);
    }

    /** Partial update of regular user */
    @PatchMapping("/partialupdate/{id}")
    public ResponseEntity<RegularUserDto> partialUpdateRegular(
            @PathVariable Long id, @RequestBody Map<String, Object> field) {
        return new ResponseEntity<>(
                regularUserService.partialRegularUser(id, field), HttpStatus.OK);
    }
}
