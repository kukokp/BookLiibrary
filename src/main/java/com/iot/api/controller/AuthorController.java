package com.iot.api.controller;

import com.iot.api.model.Author;
import com.iot.api.model.common.SuccessResponse;
import com.iot.api.model.request.create.AuthorCreationRequest;
import com.iot.api.model.request.filter.AuthorFilter;
import com.iot.api.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/create-author")
    public ResponseEntity<Author> createAuthor (@RequestBody AuthorCreationRequest authorCreationRequest) {
        return ResponseEntity.ok(authorService.createAuthor(authorCreationRequest));
    }
    @PostMapping("/get-author")
    public ResponseEntity<SuccessResponse<Author>> getAuthor (@RequestBody AuthorFilter authorFilter) {
        return ResponseEntity.ok(authorService.getAllAuthor(authorFilter));
    }

//    @PostMapping("/get-author")
//    public ResponseEntity<List<Author>> getAuthor1 (@RequestBody AuthorFilter authorFilter) {
//        return ResponseEntity.ok( HttpStatus.OK.value(),
//                HttpStatus.OK.name(),authorService.getAllAuthor(authorFilter));
//    }

    @PostMapping("/update-author")
    public ResponseEntity<SuccessResponse<Author>> updateAuthor (@RequestBody Author author) {
        return ResponseEntity.ok(authorService.updateAuthor(author));
    }


}
