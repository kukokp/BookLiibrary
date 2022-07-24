package com.iot.api.repository;

import com.iot.api.model.Author;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
