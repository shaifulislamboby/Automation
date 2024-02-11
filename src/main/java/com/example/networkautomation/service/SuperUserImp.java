package com.example.networkautomation.service;

import com.example.networkautomation.dto.SuperUserDto;
import com.example.networkautomation.entity.SuperUserEntity;
import com.example.networkautomation.repository.RegularUserRepository;
import com.example.networkautomation.repository.SuperUserRepository;
import jakarta.transaction.Transactional;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

/** Implementing all the method of superuser service class */
@Service
public class SuperUserImp implements SuperUserService {
    private final ModelMapper modelMapper = new ModelMapper();
    private final SuperUserRepository superUserRepository;

    private final RegularUserRepository regularUserRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * Injected
     *
     * @param superUserRepository
     * @param regularUserRepository
     * @param passwordEncoder
     */
    @Autowired
    public SuperUserImp(
            SuperUserRepository superUserRepository,
            RegularUserRepository regularUserRepository,
            PasswordEncoder passwordEncoder) {
        this.superUserRepository = superUserRepository;
        this.regularUserRepository = regularUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*
     * Dummy superuser has created
     * */
    @Override
    public boolean createDummySuperUser(SuperUserDto superUserDto, String username) {
        if (superUserRepository.findByUserName("cherry123") == null
                || !"cherry123".equals(username)) {
            throw new SuperUserException("You are not allowed to create a superuser.");
        }

        if ("SUPER".equals(superUserDto.getIsSuperUser())) {
            SuperUserEntity superUserEntity = modelMapper.map(superUserDto, SuperUserEntity.class);

            superUserEntity.setIsSuperUser("ROLE_" + superUserEntity.getIsSuperUser());
            superUserEntity.setCreatedOn(getFormattedCreatedOn());
            superUserEntity.setPassword(passwordEncoder.encode(superUserEntity.getPassword()));
            superUserEntity.setCreatedBy(username);

            superUserRepository.save(superUserEntity);

            //deleteSuperUser("cherry123");
            return true;
        }

        return false;
    }

    /*
     * Creating a new superuser
     * */
    @Override
    public void createNewSuperUser(SuperUserDto superUserDto, String username) {
        SuperUserEntity isSuperUser = superUserRepository.findByUserName(username);

        if (isSuperUser == null || !"SUPER".equals(superUserDto.getIsSuperUser())) {
            throw new SuperUserException("You are not allowed-(");
        } else {
            SuperUserEntity tranSuperUserEntity =
                    modelMapper.map(superUserDto, SuperUserEntity.class);

            tranSuperUserEntity.setIsSuperUser("ROLE_" + tranSuperUserEntity.getIsSuperUser());
            tranSuperUserEntity.setCreatedBy(username);
            tranSuperUserEntity.setCreatedOn(getFormattedCreatedOn());
            // tranSuperUserEntity.setPassword(passwordEncoder.encode(tranSuperUserEntity.getPassword()));

            superUserRepository.save(tranSuperUserEntity);
        }
    }

    /*
     * Creating regular user
     * */

    /*
     * Updating the superuser
     * */
    @Override
    public SuperUserDto updateTheSuperUser(SuperUserDto superUserDto, Long id) {

        if (!(Objects.equals(superUserDto.getIsSuperUser(), "SUPER")
                || Objects.equals(superUserDto.getIsSuperUser(), null))) {
            throw new SuperUserException("You can't change user's role........");
        }
        SuperUserEntity superuserEntity = superUserRepository.findById(id).get();

        superUserDto.setId(superuserEntity.getId());
        superUserDto.setCreatedBy(superuserEntity.getCreatedBy());
        superUserDto.setCreatedOn(superuserEntity.getCreatedOn());
        superUserDto.setLastLogin(getFormattedCreatedOn());
        superUserDto.setPassword(passwordEncoder.encode(superUserDto.getPassword()));
        superUserDto.setIsSuperUser("ROLE_SUPER");

        SuperUserEntity superUserEntity = modelMapper.map(superUserDto, SuperUserEntity.class);

        superUserRepository.save(superUserEntity);
        return modelMapper.map(superUserEntity, SuperUserDto.class);
    }

    /*
     * Updating information partially
     * */
    @Override
    public SuperUserDto partialSuperUpdate(Long id, Map<String, Object> field) {
        Optional<SuperUserEntity> superUser = superUserRepository.findById(id);

        if (field.containsKey("isSuperUser")) {
            throw new SuperUserException("You can't change user's role........");
        }
        if (field.containsKey("password")) {
            String pass = field.get("password").toString();
            field.put("password", passwordEncoder.encode(pass));
        }

        if (superUser.isPresent()) {

            field.forEach(
                    (key, value) -> {
                        Field fieldValue = ReflectionUtils.findField(SuperUserEntity.class, key);
                        fieldValue.setAccessible(true);
                        ReflectionUtils.setField(fieldValue, superUser.get(), value);
                    });

            superUserRepository.save(superUser.get());

            SuperUserDto superUserDto = modelMapper.map(superUser.get(), SuperUserDto.class);

            return superUserDto;
        }
        return null;
    }

    /*
     * Getting all superuser
     * */
    @Override
    public List<SuperUserDto> getAllSuperUser() {
        List<SuperUserEntity> superUserEntities = superUserRepository.findAll();

        List<SuperUserDto> superUserDtoList = new ArrayList<>();

        for (SuperUserEntity superUser : superUserEntities) {
            superUserDtoList.add(modelMapper.map(superUser, SuperUserDto.class));
        }

        return superUserDtoList;
    }

    /*
     * Deleting superuser from database
     * */
    @Override
    public void deleteSuperuser(Long id) {
        superUserRepository.deleteById(id);
    }

    /*
     * Getting information about a superuser
     * */
    @Override
    public SuperUserDto getSuperUserInfo(Long id) {
        SuperUserEntity superUserEntity = superUserRepository.findById(id).get();

        SuperUserDto superUserDto = modelMapper.map(superUserEntity, SuperUserDto.class);

        return superUserDto;
    }

    /*
     * set the format of Date in yyyy-MM-dd HH:mm format
     * */
    public String getFormattedCreatedOn() {
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(dateformat);
    }

    /*
     * Delete Dummy superuser
     * */
    @Transactional
    @Override
    public void deleteSuperUser(String username) {
        superUserRepository.deleteByUserName(username);
    }
}
