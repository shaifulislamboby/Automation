package com.example.networkautomation.service;

import com.example.networkautomation.dto.RegularUserDto;
import com.example.networkautomation.dto.SuperUserDto;
import com.example.networkautomation.entity.RegularUserEntity;
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
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

/** Service class */
@Service
public class RegularUserServiceImp implements RegularUserService {
    private SuperUserRepository superUserRepository;
    private RegularUserRepository regularUserRepository;
    private ModelMapper modelMapper = new ModelMapper();
    private PasswordEncoder passwordEncoder;

    /**
     * Injecting using constructor
     *
     * @param superUserRepository
     * @param regularUserRepository
     * @param passwordEncoder
     */
    public RegularUserServiceImp(
            SuperUserRepository superUserRepository,
            RegularUserRepository regularUserRepository,
            PasswordEncoder passwordEncoder) {
        this.superUserRepository = superUserRepository;
        this.regularUserRepository = regularUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creating regular user
     *
     * @param regularUserDto
     * @param superUserId
     * @return
     */
    @Override
    public boolean createRegularUser(RegularUserDto regularUserDto, Long superUserId) {

        if (superUserRepository.findById(superUserId).isEmpty()) {
            throw new SuperUserException("Your are not allowed-)");
        }

        RegularUserEntity regularUserEntity =
                modelMapper.map(regularUserDto, RegularUserEntity.class);

        regularUserEntity.setRole("ROLE_USER");
        regularUserEntity.setPassword(passwordEncoder.encode(regularUserEntity.getPassword()));
        regularUserEntity.setCreatedBy(superUserRepository.findById(superUserId).get());
        regularUserEntity.setCreatedOn(getFormattedCreatedOn());

        regularUserRepository.save(regularUserEntity);
        return true;
    }

    /** Getting a regular user info */
    public RegularUserDto getRegularUserInfo(Long id) {
        RegularUserEntity regularUserEntity = regularUserRepository.findById(id).get();

        if (regularUserEntity == null) {
            throw new UsernameNotFoundException("Not found: " + id);
        }
        return modelMapper.map(regularUserEntity, RegularUserDto.class);
    }

    public String getFormattedCreatedOn() {
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(dateformat);
    }

    /** Update regular user info */
    public RegularUserDto updateRegularUser(RegularUserDto regularUserDto, Long id) {

        if (!(Objects.equals(regularUserDto.getRole(), "USER")
                || Objects.equals(regularUserDto.getRole(), null))) {
            throw new SuperUserException("You can't change user's role........");
        }

        RegularUserEntity regularUserEntity = regularUserRepository.findById(id).get();

        SuperUserEntity superUserEntity = regularUserEntity.getCreatedBy();

        regularUserDto.setId(regularUserEntity.getId());
        regularUserDto.setCreatedBy(modelMapper.map(superUserEntity, SuperUserDto.class));
        regularUserDto.setCreatedOn(regularUserEntity.getCreatedOn());
        regularUserDto.setLastLogin(getFormattedCreatedOn());
        regularUserDto.setPassword(passwordEncoder.encode(regularUserDto.getPassword()));
        regularUserDto.setRole("ROLE_USER");
        regularUserEntity = modelMapper.map(regularUserDto, RegularUserEntity.class);

        regularUserRepository.save(regularUserEntity);

        return modelMapper.map(regularUserEntity, RegularUserDto.class);
    }

    @Override
    public RegularUserDto partialRegularUser(Long id, Map<String, Object> field) {
        RegularUserEntity regularUserEntity = regularUserRepository.findById(id).get();

        if (field.containsKey("role")) {
            throw new SuperUserException("You can't change user's role........");
        }

        if (regularUserEntity != null) {
            field.forEach(
                    (key, value) -> {
                        Field fieldValue = ReflectionUtils.findField(RegularUserEntity.class, key);
                        fieldValue.setAccessible(true);
                        ReflectionUtils.setField(fieldValue, regularUserEntity, value);

                        if (key == "password") {
                            ReflectionUtils.setField(
                                    fieldValue,
                                    regularUserEntity,
                                    passwordEncoder.encode(value.toString()));
                        }
                    });
            regularUserRepository.save(regularUserEntity);

            return modelMapper.map(regularUserEntity, RegularUserDto.class);
        }
        return null;
    }

    @Override
    public List<RegularUserDto> getAllRegularUser() {
        List<RegularUserEntity> regularUserEntityList = regularUserRepository.findAll();

        List<RegularUserDto> regularUserDtoList = new ArrayList<>();

        for (RegularUserEntity regularUser : regularUserEntityList) {
            regularUserDtoList.add(modelMapper.map(regularUser, RegularUserDto.class));
        }

        return regularUserDtoList;
    }

    /*
     * Deleting regular user
     * */
    @Transactional
    public void deleteRegularUser(Long id) {
        regularUserRepository.deleteById(id);
    }
}
