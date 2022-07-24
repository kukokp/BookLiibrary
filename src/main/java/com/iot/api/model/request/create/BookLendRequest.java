package com.iot.api.model.request.create;

import java.util.List;

import lombok.Data;

@Data
public class BookLendRequest {
    private List<String> bookIds;
    private String memberId;
}
