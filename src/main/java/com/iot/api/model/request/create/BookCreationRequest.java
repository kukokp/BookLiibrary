package com.iot.api.model.request.create;

import lombok.Data;

@Data
public class BookCreationRequest {
    private String name;
    private String isbn;
    private String authorId;
}
