package com.example.networkautomation.service;

import com.example.networkautomation.dto.RegularUserDto;
import java.util.List;
import java.util.Map;

/** Regular user service class to handle all business logic. */
public interface RegularUserService {
    /*
     * Creating a new user
     * */
    boolean createRegularUser(RegularUserDto regularUserDto, Long superUserId);

    /*
     * Getting a regular user information
     * */
    RegularUserDto getRegularUserInfo(Long id);

    /*
     * Update a regular user
     * */
    RegularUserDto updateRegularUser(RegularUserDto regularUserDto, Long id);

    /*
     * Partial update of a regular user
     * */
    RegularUserDto partialRegularUser(Long id, Map<String, Object> field);

    /*
     * Deleting a regular user
     * */
    void deleteRegularUser(Long id);

    /*
     * Get all regular user
     * */
    List<RegularUserDto> getAllRegularUser();
}
