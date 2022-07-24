package com.iot.api.model.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
public class SuccessResponse<T>{
    private int status= HttpStatus.OK.value();
    private String message = HttpStatus.OK.name();
    private List<T> data=new ArrayList<>();
    private Version version=new Version();
}


