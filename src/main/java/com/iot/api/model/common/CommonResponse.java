package com.iot.api.model.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
public class CommonResponse<T>{
    private int status;
    private String message = "";
    private List<T> data=new ArrayList<>();
    private Version version=new Version();
}


