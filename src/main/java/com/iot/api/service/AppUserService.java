package com.iot.api.service;

import com.iot.api.model.Author;
import com.iot.api.model.Book;
import com.iot.api.model.common.SuccessResponse;
import com.iot.api.model.request.create.AppUserCreationRequest;
import com.iot.api.model.request.create.AuthorCreationRequest;
import com.iot.api.model.request.filter.AuthorFilter;
import com.iot.api.model.user.AppUser;
import com.iot.api.repository.AppUserRepository;
import com.iot.api.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AuthorRepository authorRepository;


    public AppUser createAppUser(AppUserCreationRequest request) {
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(request, appUser);
        return appUserRepository.save(appUser);
    }

    public SuccessResponse getAppUserById(String id) {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setStatus(HttpStatus.OK.value());
        successResponse.setMessage(HttpStatus.OK.name());
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isPresent()) {
            successResponse.setData(Collections.singletonList(appUser));
        } else {
            successResponse.setStatus(HttpStatus.NOT_EXTENDED.value());
            successResponse.setMessage("Cant find any appUser under given ID");
        }
        return successResponse;
    }


    public SuccessResponse getAllAuthor(AuthorFilter authorFilter) {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setData(Collections.singletonList(authorRepository.findAll()));
        successResponse.setStatus(HttpStatus.OK.value());
        successResponse.setMessage(HttpStatus.OK.name());
        return successResponse;
    }

    public SuccessResponse updateAuthor(Author reqAuthor) {
        SuccessResponse successResponse = new SuccessResponse();
        Optional<Author> optionalAuthor = authorRepository.findById(reqAuthor.getId());
        if (!optionalAuthor.isPresent()) {
            successResponse.setStatus(HttpStatus.NOT_MODIFIED.value());
            successResponse.setMessage("Author not present in the database");
//            throw new EntityNotFoundException("");
        } else {
            Author author = optionalAuthor.get();
            author.setLastName(reqAuthor.getLastName());
            author.setFirstName(reqAuthor.getFirstName());
            author.setId(reqAuthor.getId());
            Author savedAuthor = authorRepository.save(author);
            if (savedAuthor != null && savedAuthor.getId() != null) {
                successResponse.setData(Collections.singletonList(authorRepository.findById(savedAuthor.getId())));
                successResponse.setStatus(HttpStatus.OK.value());
                successResponse.setMessage(HttpStatus.OK.name());
            } else {
                successResponse.setStatus(HttpStatus.NOT_MODIFIED.value());
                successResponse.setMessage(HttpStatus.NOT_MODIFIED.name());
            }
        }
        return successResponse;
    }


}
