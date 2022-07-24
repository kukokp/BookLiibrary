package com.iot.api.repository;

import com.iot.api.model.Member;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, String> {
}
