package com.iot.api.repository;

import com.iot.api.model.user.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AppUserRepository extends MongoRepository<AppUser, String> {

    Optional<AppUser> findByUserName(String username);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
}
