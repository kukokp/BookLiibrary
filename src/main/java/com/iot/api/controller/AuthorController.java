package com.iot.api.controller;

import com.iot.api.model.Author;
import com.iot.api.model.common.SuccessResponse;
import com.iot.api.model.request.create.AuthorCreationRequest;
import com.iot.api.model.request.filter.AuthorFilter;
import com.iot.api.model.user.AppRoleConstants;
import com.iot.api.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/create-author")
    @PreAuthorize("hasRole('ROLE_AUTHOR_ADD')")
    public ResponseEntity<Author> createAuthor (@RequestBody AuthorCreationRequest authorCreationRequest) {
        return ResponseEntity.ok(authorService.createAuthor(authorCreationRequest));
    }
    @PostMapping("/get-author")
    @PreAuthorize("hasRole('ROLE_AUTHOR_READ')")
    public ResponseEntity<SuccessResponse<Author>> getAuthor (@RequestBody AuthorFilter authorFilter) {
        return ResponseEntity.ok(authorService.getAllAuthor(authorFilter));
    }

    @PostMapping("/update-author")
    @PreAuthorize("hasRole('ROLE_AUTHOR_UPDATE')")
    public ResponseEntity<SuccessResponse<Author>> updateAuthor (@RequestBody Author author) {
        return ResponseEntity.ok(authorService.updateAuthor(author));
    }


}
