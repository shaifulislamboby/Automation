package com.example.networkautomation.repository;

import com.example.networkautomation.entity.SuperUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Declaring the CRUD operation */
@Repository
public interface SuperUserRepository extends JpaRepository<SuperUserEntity, Long> {
    SuperUserEntity findByUserName(String userName);

    void deleteByUserName(String userName);
}
