package com.example.networkautomation.repository;

import com.example.networkautomation.entity.RegularUserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** Operation of regular user */
public interface RegularUserRepository extends JpaRepository<RegularUserEntity, Long> {
    Optional<RegularUserEntity> findByUserName(String name);
}
