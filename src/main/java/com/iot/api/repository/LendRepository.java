package com.iot.api.repository;

import com.iot.api.model.Book;
import com.iot.api.model.Lend;
import com.iot.api.model.LendStatus;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LendRepository extends MongoRepository<Lend, String> {
    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
