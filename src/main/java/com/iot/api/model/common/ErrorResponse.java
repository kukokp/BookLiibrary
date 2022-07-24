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
public class ErrorResponse<T>{
    private int status = HttpStatus.FORBIDDEN.value();
    private String message = HttpStatus.FORBIDDEN.name();
    private Version version=new Version();
}


