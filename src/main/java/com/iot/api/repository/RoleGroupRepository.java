package com.iot.api.repository;

import com.iot.api.model.user.RoleGroup;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleGroupRepository extends MongoRepository<RoleGroup, String> {

    boolean existsByGroupName(String username);

}
