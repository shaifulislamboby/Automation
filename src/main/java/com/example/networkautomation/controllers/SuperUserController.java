package com.example.networkautomation.controllers;

import com.example.networkautomation.dto.RegularUserDto;
import com.example.networkautomation.dto.SuperUserDto;
import com.example.networkautomation.service.RegularUserService;
import com.example.networkautomation.service.SuperUserService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Super user REST API */
@RestController
@RequestMapping("/automation/superuser")
public class SuperUserController {
    private SuperUserService superUserService;
    private RegularUserService regularUserService;

    /** Injectting superuser & regular user service */
    @Autowired
    public SuperUserController(
            SuperUserService superUserService, RegularUserService regularUserService) {
        this.superUserService = superUserService;
        this.regularUserService = regularUserService;
    }
    // -------------- Start of GetMapping ----------------------
    /** Getting superuser info */
    @GetMapping("/superuserinfo/{id}")
    public ResponseEntity<SuperUserDto> getSuperUserInfo(@PathVariable Long id) {
        return new ResponseEntity<>(superUserService.getSuperUserInfo(id), HttpStatus.OK);
    }
    /** GET-> Getting a specific regular user information */
    @GetMapping("/regularuserinfo/{id}")
    public ResponseEntity<RegularUserDto> getUserInformation(@PathVariable Long id) {
        return new ResponseEntity<>(regularUserService.getRegularUserInfo(id), HttpStatus.OK);
    }
    /** Getting all superusers */
    @GetMapping("/allsuperusers")
    public ResponseEntity<List<SuperUserDto>> getAllSuperUser() {
        return new ResponseEntity<>(superUserService.getAllSuperUser(), HttpStatus.OK);
    }
    /** Get information about all regular users */
    @GetMapping("/getallregular")
    public ResponseEntity<List<RegularUserDto>> getAllRegularUser() {
        return new ResponseEntity<>(regularUserService.getAllRegularUser(), HttpStatus.OK);
    }
    //---------------------- End of GetMapping --------------------------

    //#########################################################################################################

    //----------------------------------------- Start of PostMapping -----------------------------------------

    /** An endpoint for creating a dummy superuser. */
    @PostMapping("/dummy/{usernamepar}")
    public ResponseEntity<HttpStatus> demoMethod(
            @RequestBody SuperUserDto superUserDto, @PathVariable String usernamepar) {
        boolean isCreated = superUserService.createDummySuperUser(superUserDto, usernamepar);

        if (isCreated) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /** Creating a new superuser */
    @PostMapping("/createnewsuperuser/{usernamepar}")
    public ResponseEntity<String> createNewUser(
            @PathVariable String usernamepar, @RequestBody SuperUserDto superUserDto) {
        superUserService.createNewSuperUser(superUserDto, usernamepar);

        return new ResponseEntity<>("New Super user has crated", HttpStatus.CREATED);
    }

    /** POST-> Creating new regular user */
    @PostMapping("/createregularuser/{id}")
    public ResponseEntity<HttpStatus> createRegularUser(
            @RequestBody RegularUserDto regularUserDto, @PathVariable Long id) {

        if (regularUserService.createRegularUser(regularUserDto, id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    //------------------------- End of PostMapping --------------------

    //------------------------- Start of PutMapping -------------------
    /** Updating the data of a superuser */
    @PutMapping("/updatesuperuser/{id}")
    public ResponseEntity<SuperUserDto> updateSuperUser(
            @RequestBody SuperUserDto superUserDto, @PathVariable Long id) {

        return new ResponseEntity<>(
                superUserService.updateTheSuperUser(superUserDto, id), HttpStatus.OK);
    }

    /** Update a regular user */
    @PutMapping("/updateregularuser/{id}")
    public ResponseEntity<RegularUserDto> updateRegularUser(
            @RequestBody RegularUserDto regularUserDto, @PathVariable Long id) {
        return new ResponseEntity<>(
                regularUserService.updateRegularUser(regularUserDto, id), HttpStatus.OK);
    }
    //-------------------------- End of PutMapping ----------------------

    //-------------------------- Start of PatchMapping ------------------
    /** Updating super user's data partially */
    @PatchMapping("/partial/{id}")
    public ResponseEntity<SuperUserDto> partialSuperuserUpdate(
            @PathVariable Long id, @RequestBody Map<String, Object> field) {
        return new ResponseEntity<>(superUserService.partialSuperUpdate(id, field), HttpStatus.OK);
    }

    /** Updating regular user's data partially */
    @PatchMapping("/pudregular/{id}")
    public ResponseEntity<RegularUserDto> partialUpdateRegularUser(
            @PathVariable Long id, @RequestBody Map<String, Object> field) {
        return new ResponseEntity<>(
                regularUserService.partialRegularUser(id, field), HttpStatus.OK);
    }

    //--------------------------- End of PatchMapping ---------------------

    //--------------------------- Start of DeleteMapping ------------------
    /** Delete an existing superuser from database */
    @DeleteMapping("/deletesuperuser/{id}")
    public ResponseEntity<HttpStatus> deleteSuperUser(@PathVariable Long id) {
        superUserService.deleteSuperuser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /** Delete a regular user */
    @DeleteMapping("/deleteregular/{id}")
    public ResponseEntity<HttpStatus> deleteRegularUser(@PathVariable Long id) {
        regularUserService.deleteRegularUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //---------------------------- End of DeleteMapping ----------------------

    /** Formatting Date in yyyy-MM-dd HH:mm:ss format */
    public String getFormattedCreatedOn() {
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(dateformat);
    }
}
