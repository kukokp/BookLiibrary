package com.iot.api.repository;

import com.iot.api.model.Book;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);

    List<Book> findAllBookByAuthorId(String authorId);
}
