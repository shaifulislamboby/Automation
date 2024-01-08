package com.example.networkautomation.repository;

import com.example.networkautomation.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface SignupRepository extends CrudRepository<UserInfo, Long> {
}
