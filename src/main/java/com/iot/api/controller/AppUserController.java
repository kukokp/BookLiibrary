package com.iot.api.controller;

import com.iot.api.model.Author;
import com.iot.api.model.Book;
import com.iot.api.model.common.SuccessResponse;
import com.iot.api.model.request.create.AppUserCreationRequest;
import com.iot.api.model.request.create.AuthorCreationRequest;
import com.iot.api.model.request.create.BookCreationRequest;
import com.iot.api.model.request.filter.AuthorFilter;
import com.iot.api.model.user.AppUser;
import com.iot.api.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @PostMapping("/create-app-user")
    @PreAuthorize("hasRole('ROLE_APP_USER_ADD')")
    public ResponseEntity<AppUser> createAppUser(@RequestBody AppUserCreationRequest appUserCreationRequest) {
        return ResponseEntity.ok(appUserService.createAppUser(appUserCreationRequest));
    }

    @GetMapping("/user-detail/{user_id}")
    @PreAuthorize("hasRole('ROLE_APP_USER_READ')")
    public SuccessResponse<AppUser> getUserDetails(@PathVariable String  user_id ) {
        return appUserService.getAppUserById(user_id);
    }


    @PostMapping("/get-app-user")
    @PreAuthorize("hasRole('ROLE_APP_USER_READ')")
    public ResponseEntity<SuccessResponse<Author>> getAuthor(@RequestBody AuthorFilter authorFilter) {
        return ResponseEntity.ok(appUserService.getAllAuthor(authorFilter));
    }

    @PostMapping("/update-app-user")
    @PreAuthorize("hasRole('ROLE_APP_USER_UPDATE')")
    public ResponseEntity<SuccessResponse<Author>> updateAuthor(@RequestBody Author author) {
        return ResponseEntity.ok(appUserService.updateAuthor(author));
    }


}
