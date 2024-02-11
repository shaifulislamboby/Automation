package com.example.networkautomation.service;

import com.example.networkautomation.dto.SuperUserDto;
import java.util.List;
import java.util.Map;

/** Superuser service class */
public interface SuperUserService {

    /*
     * Create Dummy superuser*/
    boolean createDummySuperUser(SuperUserDto superUserDto, String username);
    /*
     * Creating a new superuser
     * */
    void createNewSuperUser(SuperUserDto superUserDto, String username);

    /*
     * Getting a superuser info
     * */
    SuperUserDto getSuperUserInfo(Long id);

    /*
     * Updating the existing superuser
     * */
    SuperUserDto updateTheSuperUser(SuperUserDto superUserDto, Long id);

    void deleteSuperuser(Long id);

    /*
     * Updating the info partially
     * */
    SuperUserDto partialSuperUpdate(Long id, Map<String, Object> field);

    /*
     * To get all superuser
     * */
    List<SuperUserDto> getAllSuperUser();

    /*
     * To get all regular user
     * */
    /*
     * Checking the user is valid or not
     * */
    void deleteSuperUser(String username);
}
